package org.mypro.front;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.mypro.dao.AdminMapper;
import org.mypro.dao.HistoryMapper;
import org.mypro.dao.ScenicspotMapper;
import org.mypro.dao.UserMapper;
import org.mypro.entity.Admin;
import org.mypro.entity.AdminExample;
import org.mypro.entity.History;
import org.mypro.entity.HistoryExample;
import org.mypro.entity.Scenicspot;
import org.mypro.entity.ScenicspotExample;
import org.mypro.entity.User;
import org.mypro.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Controller

@RequestMapping(value = "/")

public class loginAndRegistController {

	private static final Log logger = LogFactory.getLog(loginAndRegistController.class);

	@Autowired
	private AdminMapper admindao;

	@Autowired
	private UserMapper userdao;
	
	@Autowired
	private ScenicspotMapper scenicspotdao;
	
	@Autowired
	private HistoryMapper historydao;
	
	@ResponseBody
	@RequestMapping(value = "shoucang")
	public String shoucang(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer scenicspotid,Integer userid) {
		
		History history = new History();
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Scenicspot scenicspot = scenicspotdao.selectByPrimaryKey(scenicspotid);
		User user = userdao.selectByPrimaryKey(userid);
		
		history.setAddtime(sdf.format(d));
		history.setScenicspot(scenicspot.getName());
		history.setScenicspotid(scenicspot.getId());
		history.setUser(user.getName());
		history.setUserid(userid);
		
		historydao.insert(history);
		
		// 返回登录页
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "quxiaoshoucang")
	public String quxiaoshoucang(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer scenicspotid,Integer userid) {
		
		HistoryExample example = new HistoryExample();
		HistoryExample.Criteria criteria = example.createCriteria();
		criteria.andScenicspotidEqualTo(scenicspotid);
		criteria.andUseridEqualTo(userid);
		
		historydao.deleteByExample(example);
		

		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "shoucangshu")
	public Long shoucangshu(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer scenicspotid,Integer userid) {
		
		HistoryExample example = new HistoryExample();
		HistoryExample.Criteria criteria = example.createCriteria();
		criteria.andScenicspotidEqualTo(scenicspotid);
		criteria.andUseridEqualTo(userid);
		
		Long shoucangnum = historydao.countByExample(example);
		
		return shoucangnum;
	}
	
	//测试数据，手机端推荐信息传送
	@ResponseBody
	@RequestMapping(value = "cainixihuanjson")
	public Map cainixihuanjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer userID) {
		
		Map result = new HashMap();

		if ("".equals(userID) || "null".equals(userID) || userID == null  || "undifend".equals(userID)) {

			ScenicspotExample example = new ScenicspotExample();

			List scenicspotall = scenicspotdao.selectByExample(example);

			result.put("scenicspotall", scenicspotall);

			return result;
		}

		DataModel dataModel = null;
		try {
			// 文件数据转换成数据模型
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setDatabaseName("ahjdtj");

			dataModel = new MySQLJDBCDataModel(dataSource, "t_history", "userid", "scenicspotid", "val", "addtime");

			/**
			 * 物品相似度定义
			 */
			// 余弦相似度
			ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
			// 欧几里得相似度
			// ItemSimilarity itemSimilarity= new
			// EuclideanDistanceSimilarity(dataModel);
			// //皮尔森相似度
			// ItemSimilarity itemSimilarity = new
			// PearsonCorrelationSimilarity(dataModel);
			// 定义推荐引擎
			Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
			// 获取物品迭代器
			LongPrimitiveIterator itemIDIterator = dataModel.getItemIDs();
			// 遍历所有物品
			while (itemIDIterator.hasNext()) {
				System.out.println("==================================================");
				Long itermID = itemIDIterator.next();
				LongPrimitiveIterator otherItemIDIterator = dataModel.getItemIDs();
				// 打印物品相似度
				while (otherItemIDIterator.hasNext()) {
					Long otherItermID = otherItemIDIterator.next();
					System.out.println("物品 " + itermID + " 与物品 " + otherItermID + " 的相似度为： "
							+ itemSimilarity.itemSimilarity(itermID, otherItermID));
				}
			}

			List<RecommendedItem> itemList = recommender.recommend(userID, 2);

			List<Integer> finalRecomend = new ArrayList<Integer>();

			finalRecomend.add(-1);

			if (itemList.size() > 0) {
				for (RecommendedItem item : itemList) {
					System.out.println("用户 " + userID + " 推荐物品 " + item.getItemID() + ",物品评分 " + item.getValue());

					finalRecomend.add((int) item.getItemID());
				}
			} else {
				System.out.println("用户 " + userID + " 无任何物品推荐");
			}

			ScenicspotExample example = new ScenicspotExample();

			ScenicspotExample.Criteria criteria = example.createCriteria();

			criteria.andIdIn(finalRecomend);

			List scenicspotall = scenicspotdao.selectByExample(example);

			if (scenicspotall.isEmpty()) {
				ScenicspotExample example2 = new ScenicspotExample();
				
				example2.setOrderByClause("rand()");
				
				scenicspotall = scenicspotdao.selectByExample(example2);
			}

			result.put("scenicspotall", scenicspotall);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}
	

	@RequestMapping(value = "cainixihuan")
	public String cainixihuan(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer userID) {
		

		if ("".equals(userID) || "null".equals(userID) || userID == null  || "undifend".equals(userID)) {

			ScenicspotExample example = new ScenicspotExample();

			List scenicspotall = scenicspotdao.selectByExample(example);

			request.setAttribute("scenicspotall", scenicspotall);

			return "cainixihuan";
		}

		DataModel dataModel = null;
		try {
			// 文件数据转换成数据模型
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setDatabaseName("ahjdtj");
			//val做评分用 固定为1
			dataModel = new MySQLJDBCDataModel(dataSource, "t_history", "userid", "scenicspotid", "val", "addtime");

			/**
			 * 物品相似度定义
			 */
			// 余弦相似度
			ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
			// 欧几里得相似度
			// ItemSimilarity itemSimilarity= new
			// EuclideanDistanceSimilarity(dataModel);
			// //皮尔森相似度
			// ItemSimilarity itemSimilarity = new
			// PearsonCorrelationSimilarity(dataModel);
			// 定义推荐引擎
			Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
			// 获取物品迭代器
			LongPrimitiveIterator itemIDIterator = dataModel.getItemIDs();
			// 遍历所有物品
			while (itemIDIterator.hasNext()) {
				System.out.println("==================================================");
				Long itermID = itemIDIterator.next();
				LongPrimitiveIterator otherItemIDIterator = dataModel.getItemIDs();
//				// 打印物品相似度
//				while (otherItemIDIterator.hasNext()) {
//					Long otherItermID = otherItemIDIterator.next();
//					System.out.println("物品 " + itermID + " 与物品 " + otherItermID + " 的相似度为： "
//							+ itemSimilarity.itemSimilarity(itermID, otherItermID));
//				}
			}

			List<RecommendedItem> itemList = recommender.recommend(userID, 2);

			List<Integer> finalRecomend = new ArrayList<Integer>();

			finalRecomend.add(-1);

			if (itemList.size() > 0) {
				for (RecommendedItem item : itemList) {
					System.out.println("用户 " + userID + " 推荐物品 " + item.getItemID() + ",物品评分 " + item.getValue());

					finalRecomend.add((int) item.getItemID());

				}
			} else {
				System.out.println("用户 " + userID + " 无任何物品推荐");
			}

			ScenicspotExample example = new ScenicspotExample();

			ScenicspotExample.Criteria criteria = example.createCriteria();

			criteria.andIdIn(finalRecomend);

			List scenicspotall = scenicspotdao.selectByExample(example);

			if (scenicspotall.isEmpty()) {
				ScenicspotExample example2 = new ScenicspotExample();
				
				example2.setOrderByClause("rand()");
				
				scenicspotall = scenicspotdao.selectByExample(example2);
			}

			request.setAttribute("scenicspotall", scenicspotall);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "cainixihuan";

	}
	
	@ResponseBody
	@RequestMapping(value = "fangwenlishi")
	public String fangwenlishi(HttpServletRequest request, HttpServletResponse response, HttpSession session,Integer scenicspotid,Integer userid) {
		// 输出日志，当前正在执行程序loginAndRegistController.fangwenlishi
		logger.debug("loginAndRegistController.fangwenlishi ......");
		
		User user = userdao.selectByPrimaryKey(userid);
		
		//判断是否存在访问记录
		History history = new History();
		
		Scenicspot scenicspot = scenicspotdao.selectByPrimaryKey(scenicspotid);
		
		HistoryExample example = new HistoryExample();
		HistoryExample.Criteria criteria = example.createCriteria();
		
		criteria.andUseridEqualTo(userid);
		criteria.andScenicspotidEqualTo(scenicspot.getId());
		
		List<History> historyall = historydao.selectByExample(example);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(historyall.isEmpty()){
			history.setAddtime(sdf.format(d));
			history.setScenicspot(scenicspot.getName());
			history.setScenicspotid(scenicspot.getId());
			history.setUser(user.getName());
			history.setUserid(userid);
			
			historydao.insert(history);
		}else{
			historyall.get(0).setAddtime(sdf.format(d));
			historydao.updateByPrimaryKey(historyall.get(0));
		}

		return "success";
	}
	
	// 定义login方法，跳转到login页面
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 输出日志，当前正在执行程序loginAndRegistController.login
		logger.debug("loginAndRegistController.login ......");

		// 返回登录页
		return "login";
	}

	// 定义regist方法，跳转到regist页面

	@RequestMapping(value = "regist")

	public String regist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		// 输出日志，当前正在执行程序为loginAndRegistController.regist

		logger.debug("loginAndRegistController.regist ......");

		// 返回regist页面

		return "regist";

	}

	// 定义loginact，处理登录

	@RequestMapping(value = "loginact")

	public String loginact(HttpServletRequest request, HttpServletResponse response, Admin admin, String identity,
			HttpSession session) {

		// 输出登录日志，表示当前正在执行loginAndRegistController.loginact

		logger.debug("loginAndRegistController.loginact ......"); // 判断管理员是否与
																	// identity一致

		if ("管理员".equals(identity)) {

			// 实例化AdminExample

			AdminExample example = new AdminExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'
			// and password = '" + admin.getPassword() + "'");

			// 实例化AdminExample类的内部类Criteria

			AdminExample.Criteria criteria = example.createCriteria();

			// 设置查询参数username等于页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 设置查询参数password等于页面传入的password

			criteria.andPasswordEqualTo(admin.getPassword());

			// 查询出管理员表中，username和password符合要求数据

			List<Admin> admins = admindao.selectByExample(example);

			// 如果结果为空

			if (admins.isEmpty()) {

				// 将账户或密码错误保存到request中

				request.setAttribute("message", "账号或密码错误");

				// 返回登录页面

				return "login";

			} else {

				// 登录成功，将名字保存到session中

				session.setAttribute("mingzi", admins.get(0).getUsername());

				// 登录成功，将登录用户id保存到session中

				session.setAttribute("id", admins.get(0).getId());

				// 登录成功，将登录用户保存到session中

				session.setAttribute("userinfo", admins.get(0));

				// 登录成功，将登录用户身份保存到session中

				session.setAttribute("identity", identity);

				return "adminindex";

			}

		} // 判断用户是否与 identity一致

		if ("用户".equals(identity)) {

			// 实例化UserExample

			UserExample example = new UserExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'
			// and password = '" + admin.getPassword() + "'");

			// 实例化UserExample类的内部类Criteria

			UserExample.Criteria criteria = example.createCriteria();

			// 设置查询参数username等于页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 设置查询参数password等于页面传入的password

			criteria.andPasswordEqualTo(admin.getPassword());

			// 查询出用户表中，username和password符合要求数据

			List<User> admins = userdao.selectByExample(example);

			// 如果结果为空

			if (admins.isEmpty()) {

				// 将账户或密码错误保存到request中

				request.setAttribute("message", "账号或密码错误");

				// 返回登录页面

				return "login";

			} else {

				// 登录成功，将名字保存到session中

				session.setAttribute("mingzi", admins.get(0).getName());

				// 登录成功，将登录用户id保存到session中

				session.setAttribute("id", admins.get(0).getId());

				// 登录成功，将登录用户保存到session中

				session.setAttribute("userinfo", admins.get(0));

				// 登录成功，将登录用户身份保存到session中

				session.setAttribute("identity", identity);

				return "userindex";

			}

		} // 将请选择登录身份保存到request的message中

		request.setAttribute("message", "请选择登录身份");

		// 返回到登录页

		return "login";

	}

	// 定义registact，处理注册

	@RequestMapping(value = "registact")

	public String registact(HttpServletRequest request, HttpServletResponse response, Admin admin, String identity,
			HttpSession session, String repassword) throws Exception {

		// 输出注册日志，表示当前正在执行loginAndRegistController.registact

		logger.debug("loginAndRegistController.registact ......"); // 判断两次密码是否一致

		if (!repassword.equals(admin.getPassword())) {

			// 将两次密码不一致信息保存到message中

			request.setAttribute("message", "两次密码不一致");
			return "regist";
		} // 判断管理员和identity是否一致

		if ("管理员".equals(identity)) {

			// 实例化AdminExample

			AdminExample example = new AdminExample();

			// example.setWhere("and username = '" + admin.getUsername() + "");

			// 实例化AdminExample的内部类Criteria

			AdminExample.Criteria criteria = example.createCriteria();

			// 设置查询条件username为页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 根据上方查询条件查询管理员表中username为传入username的数据

			List admins = admindao.selectByExample(example);

			// 如果查询结果不为空

			if (!admins.isEmpty()) {

				// 将该账户已存在信息保存到request中的message中

				request.setAttribute("message", "该账号已存在");

				// 返回注册页

				return "regist";

			} else {

				// 使用admindao的 insert方法将页面传入的管理员数据添加到数据库中

				admindao.insert(admin);

				// 将注册成功信息保存到request的message中

				request.setAttribute("message", "注册成功，请登录");

				// 返回登录页

				return "login";

			}

		} // 判断用户和identity是否一致

		if ("用户".equals(identity)) {

			// 实例化UserExample

			UserExample example = new UserExample();

			// example.setWhere("and username = '" + admin.getUsername() + "");

			// 实例化UserExample的内部类Criteria

			UserExample.Criteria criteria = example.createCriteria();

			// 设置查询条件username为页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 根据上方查询条件查询用户表中username为传入username的数据

			List admins = userdao.selectByExample(example);

			// 实例化User

			User user = new User();

			// 设置user的username为admin的username

			user.setUsername(admin.getUsername());

			// 设置user的password为admin的password

			user.setPassword(admin.getPassword());

			// 如果查询结果不为空

			if (!admins.isEmpty()) {

				// 将该账户已存在信息保存到request中的message中

				request.setAttribute("message", "该账号已存在");

				// 返回注册页

				return "regist";

			} else {
				
				user.setName("用户" + new Date().getTime());
				
				// 使用userdao的 insert方法将页面传入的用户数据添加到数据库中

				userdao.insert(user);

				// 将注册成功信息保存到request的message中

				request.setAttribute("message", "注册成功，请登录");

				// 返回登录页

				return "login";

			}

		} // 将请选择注册身份保存到request的message中

		request.setAttribute("message", "请选择注册身份");

		// 返回到登录页

		return "regist";

	}

	@ResponseBody
	// 定义loginactjson，处理登录

	@RequestMapping(value = "loginactjson")

	public Map loginactjson(HttpServletRequest request, HttpServletResponse response, @RequestBody Map canshu,
			HttpSession session) {

		// 输出登录日志，表示当前正在执行loginAndRegistController.loginactjson

		logger.debug("loginAndRegistController.loginactjson ......");
		Map result = new HashMap();
		// 从传入参数中获取身份信息
		String identity = (String) canshu.get("identity");
		// 生成用户对象
		Admin admin = new Admin();
		// 从传入参数中获取账号信息
		admin.setUsername((String) canshu.get("username"));
		// 从传入参数中获取密码信息
		admin.setPassword((String) canshu.get("password")); // 判断管理员是否与
															// identity一致

		if ("管理员".equals(identity)) {

			// 实例化AdminExample

			AdminExample example = new AdminExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'
			// and password = '" + admin.getPassword() + "'");

			// 实例化AdminExample类的内部类Criteria

			AdminExample.Criteria criteria = example.createCriteria();

			// 设置查询参数username等于页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 设置查询参数password等于页面传入的password

			criteria.andPasswordEqualTo(admin.getPassword());

			// 查询出管理员表中，username和password符合要求数据

			List<Admin> admins = admindao.selectByExample(example);

			// 如果结果为空

			if (admins.isEmpty()) {

				// 将账户或密码错误保存到message中

				result.put("message", "账户或密码错误");
				// 返回resultmap对象

				return result;
			} else {

				// 登录成功，将登录成功保存到message中

				result.put("message", "登录成功");
				// 登录成功，将名字保存到result中

				result.put("mingzi", admins.get(0).getUsername());
				// 登录成功，将登录用户id保存到result中

				result.put("id", admins.get(0).getId());
				// 登录成功，将登录用户保存到result中

				result.put("userinfo", admins.get(0));
				// 登录成功，将登录用户身份保存到result中

				result.put("identity", identity);

				// 返回resultmap对象

				return result;
			}

		} // 判断用户是否与 identity一致

		if ("用户".equals(identity)) {

			// 实例化UserExample

			UserExample example = new UserExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'
			// and password = '" + admin.getPassword() + "'");

			// 实例化UserExample类的内部类Criteria

			UserExample.Criteria criteria = example.createCriteria();

			// 设置查询参数username等于页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 设置查询参数password等于页面传入的password

			criteria.andPasswordEqualTo(admin.getPassword());

			// 查询出用户表中，username和password符合要求数据

			List<User> admins = userdao.selectByExample(example);

			// 如果结果为空

			if (admins.isEmpty()) {

				// 将账户或密码错误保存到message中

				result.put("message", "账户或密码错误");
				// 返回resultmap对象

				return result;
			} else {

				// 登录成功，将登录成功保存到message中

				result.put("message", "登录成功");
				// 登录成功，将名字保存到result中

				result.put("mingzi", admins.get(0).getName());
				// 登录成功，将登录用户id保存到result中

				result.put("id", admins.get(0).getId());
				// 登录成功，将登录用户保存到result中

				result.put("userinfo", admins.get(0));
				// 登录成功，将登录用户身份保存到result中

				result.put("identity", identity);

				// 返回resultmap对象

				return result;
			}

		} // 将请选择登录身份保存到result的message中进行保存
		result.put("message", "请选择登录身份");
		// 将result用json格式数据返回页面
		return result;
	}

	@ResponseBody
	// 定义registactjson，处理注册

	@RequestMapping(value = "registactjson")

	public Map registactjson(HttpServletRequest request, HttpServletResponse response, @RequestBody Map canshu,
			HttpSession session) throws Exception {

		// 输出注册日志，表示当前正在执行loginAndRegistController.registactjson

		logger.debug("loginAndRegistController.registactjson ......");
		Map result = new HashMap();
		// 获取当前登录的用户身份
		// 从传入参数中获取确认密码信息
		String repassword = (String) canshu.get("repassword");
		// 从传入参数中获取身份信息
		String identity = (String) canshu.get("identity");
		// 生成用户对象
		Admin admin = new Admin();
		// 从传入参数中获取账号信息
		admin.setUsername((String) canshu.get("username"));
		// 从传入参数中获取密码信息
		admin.setPassword((String) canshu.get("password")); // 判断两次密码是否一致

		if (!repassword.equals(admin.getPassword())) {

			// 将账号已存在信息保存到result的message中进行保存
			result.put("message", "两次密码不一致");
			// 将result用json格式数据返回页面
			return result;
		} // 判断管理员和identity是否一致

		if ("管理员".equals(identity)) {

			// 实例化AdminExample

			AdminExample example = new AdminExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'");

			// 实例化AdminExample的内部类Criteria

			AdminExample.Criteria criteria = example.createCriteria();

			// 设置查询条件username为页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 根据上方查询条件查询管理员表中username为传入username的数据

			List admins = admindao.selectByExample(example);

			// 如果查询结果不为空

			if (!admins.isEmpty()) {

				// 将账号已存在信息保存到result的message中进行保存
				result.put("message", "该账号已存在");
				// 将result用json格式数据返回页面
				return result;
			} else {

				// 使用admindao的 insert方法将页面传入的管理员数据添加到数据库中

				admindao.insert(admin);

				// 将注册成功，请登录信息保存到result的message中进行保存
				result.put("message", "注册成功，请登录");
				// 将result用json格式数据返回页面
				return result;
			}

		} // 判断用户和identity是否一致

		if ("用户".equals(identity)) {

			// 实例化UserExample

			UserExample example = new UserExample();

			// example.setWhere("and username = '" + admin.getUsername() + "'");

			// 实例化UserExample的内部类Criteria

			UserExample.Criteria criteria = example.createCriteria();

			// 设置查询条件username为页面传入的username

			criteria.andUsernameEqualTo(admin.getUsername());

			// 根据上方查询条件查询用户表中username为传入username的数据

			List admins = userdao.selectByExample(example);

			// 实例化User

			User user = new User();

			// 设置user的username为admin的username

			user.setUsername(admin.getUsername());

			// 设置user的password为admin的password

			user.setPassword(admin.getPassword());

			// 如果查询结果不为空

			if (!admins.isEmpty()) {

				// 将账号已存在信息保存到result的message中进行保存
				result.put("message", "该账号已存在");
				// 将result用json格式数据返回页面
				return result;
			} else {
				
				user.setName("用户" + new Date().getTime());
				
				// 使用userdao的 insert方法将页面传入的用户数据添加到数据库中

				userdao.insert(user);

				// 将注册成功，请登录信息保存到result的message中进行保存
				result.put("message", "注册成功，请登录");
				// 将result用json格式数据返回页面
				return result;
			}

		} // 将请选择注册身份保存到result的message中进行保存
		result.put("message", "请选择注册身份");
		// 将result用json格式数据返回页面
		return result;
	}

	// 定义exitsystem方法，清除系统中的session数据

	@RequestMapping(value = "exitsystem")

	public String exitsystem(HttpServletRequest request, HttpServletResponse response, Admin admin,
			HttpSession session) {

		// 输出日志，表示当前正在执行loginAndRegistController.exitsystem

		logger.debug("loginAndRegistController.exitsystem ......");

		// 清除session中的数据信息

		session.invalidate();

		// 返回到登录页

		return "login";

	}

	// 定义adminindex方法

	@RequestMapping(value = "adminindex")

	public String adminindex(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		// 输出日志，表示当前正在执行loginAndRegistController.adminindex

		logger.debug("loginAndRegistController.adminindex ......");

		// 返回adminindex页面

		return "adminindex";

	} // 定义userindex方法

	@RequestMapping(value = "userindex")

	public String userindex(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		// 输出日志，表示当前正在执行loginAndRegistController.userindex

		logger.debug("loginAndRegistController.userindex ......");

		// 返回userindex页面

		return "userindex";

	} // 定义adminupdategerenxinxiact方法

	@RequestMapping(value = "adminupdategerenxinxiact")

	public String adminupdategerenxinxiact(HttpServletRequest request, HttpServletResponse response, Admin admin,
			HttpSession session) throws IOException {

		// 输出日志，表示正在执行loginAndRegistController.adminupdategerenxinxiact

		logger.debug("loginAndRegistController.adminupdategerenxinxiact ......");

		// 调用admindao的updateByPrimaryKey方法修改管理员信息

		admindao.updateByPrimaryKeySelective(admin);

		// 将修改后的名字同步到session中

		session.setAttribute("mingzi", admin.getUsername());

		// 将修改后的id同步到session中

		session.setAttribute("id", admin.getId());

		// 将修改后的userinfo同步到session中

		session.setAttribute("userinfo", admin);

		// 将修改个人信息成功保存到request的message中

		request.setAttribute("message", "修改个人信息成功");

		// 返回到adminindex中

		return "adminindex";

	} // 定义userupdategerenxinxiact方法

	@RequestMapping(value = "userupdategerenxinxiact")

	public String userupdategerenxinxiact(HttpServletRequest request, HttpServletResponse response, User user,
			HttpSession session) throws IOException {

		// 输出日志，表示正在执行loginAndRegistController.userupdategerenxinxiact

		logger.debug("loginAndRegistController.userupdategerenxinxiact ......");

		// 调用userdao的updateByPrimaryKey方法修改用户信息

		userdao.updateByPrimaryKeySelective(user);

		// 将修改后的名字同步到session中

		session.setAttribute("mingzi", user.getName());

		// 将修改后的id同步到session中

		session.setAttribute("id", user.getId());

		// 将修改后的userinfo同步到session中

		session.setAttribute("userinfo", user);

		// 将修改个人信息成功保存到request的message中

		request.setAttribute("message", "修改个人信息成功");

		// 返回到userindex中

		return "userindex";

	}

	// 返回json字符串
	@ResponseBody
	// 添加文件ajax处理
	@RequestMapping(value = "addfilejson")
	public String addfilejson(HttpServletRequest request, HttpServletResponse response, MultipartFile file,
			HttpSession session) throws IOException {
		// 表示当前正在执行loginAndRegistController的addfilejson方法
		logger.debug("loginAndRegistController.addfilejson ......");
		// 调用uploadUtile方法上传文件并返回文件地址
		String filepath = uploadUtile(file, request);
		// 将文件地址返回
		return filepath;
	}

	// 上传文件图片等

	public String uploadUtile(MultipartFile file, HttpServletRequest request) throws IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");

		String res = sdf.format(new Date());

		// uploads文件夹位置

		String rootPath = request.getSession().getServletContext().getRealPath("resource/uploads/");

		// 原始名称

		String originalFileName = file.getOriginalFilename();

		// 新文件名

		String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));

		// 创建年月文件夹

		Calendar date = Calendar.getInstance();

		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

		// 新文件

		File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);

		// 判断目标文件所在目录是否存在

		if (!newFile.getParentFile().exists()) {

			// 如果目标文件所在的目录不存在，则创建父目录

			newFile.getParentFile().mkdirs();

		}

		System.out.println(newFile);

		// 将内存中的数据写入磁盘

		file.transferTo(newFile);

		// 完整的url

		String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;

		return fileUrl;

	}
}
