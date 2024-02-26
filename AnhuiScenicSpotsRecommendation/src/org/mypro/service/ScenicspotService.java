package org.mypro.service;

import java.io.File;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import java.util.Calendar;

import java.util.Collections;

import java.util.Date;

import java.util.List;

import javax.jms.Session;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.mypro.dao.HistoryMapper;
import org.mypro.dao.ScenicspotMapper;
import org.mypro.entity.History;
import org.mypro.entity.HistoryExample;
import org.mypro.entity.Scenicspot;

import org.mypro.entity.ScenicspotExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Service

public class ScenicspotService implements ScenicspotServiceImp {

	private static final Log logger = LogFactory.getLog(ScenicspotService.class);

	@Autowired
	private ScenicspotMapper scenicspotdao;

	@Autowired
	private HistoryMapper historydao;
	
	// 定义方法addscenicspot,响应控制层addscenicspot请求

	public void addscenicspot(HttpServletRequest request, HttpSession session) {

		// 输出日志，当前执行方法为addscenicspot

		logger.debug("ScenicspotService.addscenicspot ......");

	}

	// 定义addscenicspotact方法

	public void addscenicspotact(HttpServletRequest request, HttpSession session, Scenicspot scenicspot)
			throws IOException {

		// 输出当前方法日志，表示正在执行ScenicspotService.addscenicspotact方法

		logger.debug("ScenicspotService.addscenicspotact ......");

		// 使用scenicspotdao的insert方法将景点添加到数据库中

		scenicspotdao.insert(scenicspot);

		// 将添加景点信息添加到request中用message参数进行保存

		request.setAttribute("message", "添加景点成功");

	} // 定义addscenicspotactjson方法

	public void addscenicspotactjson(Scenicspot scenicspot) throws IOException {

		// 输出当前方法日志，表示正在执行ScenicspotService.addscenicspotactjson方法

		logger.debug("ScenicspotService.addscenicspotactjson ......");

		// 使用scenicspotdao的insert方法将景点添加到数据库中

		scenicspotdao.insert(scenicspot);

	}

	// 定义scenicspotmanage方法响应页面请求

	public void scenicspotmanage(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行ScenicspotService.scenicspotmanage

		logger.debug("ScenicspotService.scenicspotmanage ......");

		// 实例化ScenicspotExample类为example

		ScenicspotExample example = new ScenicspotExample();

		// 使用 scenicspotdao.selectByExample(example)方法获取所有的景点数据

		List scenicspotall = scenicspotdao.selectByExample(example);

		// 将所有的景点数据保存到request中的scenicspotall参数里

		request.setAttribute("scenicspotall", scenicspotall);

	}

	// 定义 scenicspotview方法

	public void scenicspotview(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行ScenicspotService.scenicspotview方法

		logger.debug("ScenicspotService.scenicspotview ......");

		// 实例化ScenicspotExample类

		ScenicspotExample example = new ScenicspotExample();

		// 使用scenicspotdao的selectByExample方法查询景点信息

		List scenicspotall = scenicspotdao.selectByExample(example);

		// 将查询的scenicspotall保存到request中，记录为参数scenicspotall

		request.setAttribute("scenicspotall", scenicspotall);

	}

	// 定义 updatescenicspot方法

	public void updatescenicspot(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志ScenicspotController.updatescenicspot，表示正在执行该方法

		logger.debug("ScenicspotController.updatescenicspot ......");

		// 定义景点为biaoming，使用 scenicspotdao的selectByPrimaryKey方法通过id查询数据

		Scenicspot scenicspot = scenicspotdao.selectByPrimaryKey(id);

		// 将查询的景点保存到request中

		request.setAttribute("scenicspot", scenicspot);

	}

	// 定义updatescenicspotact处理景点修改

	public void updatescenicspotact(HttpServletRequest request, Scenicspot scenicspot, HttpSession session)
			throws IOException {

		// 输出日志，表示正在执行当前方法ScenicspotController.updatescenicspotact

		logger.debug("ScenicspotController.updatescenicspotact ......");

		// 执行scenicspotdao的updateByPrimaryKeySelective方法，将传入的景点数据同步到数据库中

		scenicspotdao.updateByPrimaryKeySelective(scenicspot);

		// 将修改成功信息保存到request中

		request.setAttribute("message", "修改景点信息成功");

	} // 定义updatescenicspotactjson处理景点修改

	public void updatescenicspotactjson(Scenicspot scenicspot) throws IOException {

		// 输出日志，表示正在执行当前方法ScenicspotController.updatescenicspotactjson

		logger.debug("ScenicspotController.updatescenicspotactjson ......");

		// 执行scenicspotdao的updateByPrimaryKeySelective方法，将传入的景点数据同步到数据库中

		scenicspotdao.updateByPrimaryKeySelective(scenicspot);

	}

	// 定义deletescenicspot,处理删除景点

	public void deletescenicspot(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志，表示当前正在执行ScenicspotService.deletescenicspot方法

		logger.debug("ScenicspotService.deletescenicspot ......");

		// 根据id调用scenicspotdao的deleteByPrimaryKey方法，删除景点

		scenicspotdao.deleteByPrimaryKey(id);

		request.setAttribute("message", "删除景点成功");

	}

	// 定义searchscenicspot方法，处理搜索操作

	public void searchscenicspot(HttpServletRequest request, HttpSession session, String search) {

		// 输出日志，表示当前正在执行ScenicspotService.searchscenicspot

		logger.debug("ScenicspotService.searchscenicspot ......");

		// 实例化 ScenicspotExample

		ScenicspotExample example = new ScenicspotExample();

		// 实例化 ScenicspotExample的Criteria内部类

		ScenicspotExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andNameLike("%" + search + "%");

		}

		// 查询景点结果

		List scenicspotall = scenicspotdao.selectByExample(example);

		// 将查询到的景点 保存到request中进行保存

		request.setAttribute("scenicspotall", scenicspotall);

	}

	// 定义searchscenicspotjson方法，处理搜索操作

	public Map searchscenicspotjson(String search) {

		// 输出日志，表示当前正在执行ScenicspotService.searchscenicspotjson

		logger.debug("ScenicspotService.searchscenicspotjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 实例化 ScenicspotExample

		ScenicspotExample example = new ScenicspotExample();

		// 实例化 ScenicspotExample的Criteria内部类

		ScenicspotExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andNameLike("%" + search + "%");

		}

		// 查询景点结果

		List scenicspotall = scenicspotdao.selectByExample(example);

		// 将查询到的景点 保存到request中进行保存

		result.put("scenicspotall", scenicspotall);

		// 返回查询结果resultmap

		return result;

	}

	// 定义ScenicspotpinglunMapper

	public void scenicspotdetails(HttpServletRequest request, HttpSession session, int id) {

		// 输入日志信息，表名当前执行方法为ScenicspotController.scenicspotdetails

		logger.debug("ScenicspotController.scenicspotdetails ......");
		
		
		// 传入页面所传入的景点id使用scenicspotdao的selectByPrimaryKey方法进行查询

		Scenicspot scenicspot = scenicspotdao.selectByPrimaryKey(id);
		
		//添加访问记录
		
		History history = new History();
		
		
		String identity = (String) session.getAttribute("identity");
		
		if("用户".equals(identity)){
			
			//判断是否存在访问记录
			
			HistoryExample example = new HistoryExample();
			HistoryExample.Criteria criteria = example.createCriteria();
			
			criteria.andUseridEqualTo((Integer) session.getAttribute("id"));
			criteria.andScenicspotidEqualTo(scenicspot.getId());
			
			List<History> historyall = historydao.selectByExample(example);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(historyall.isEmpty()){
				history.setAddtime(sdf.format(d));
				history.setScenicspot(scenicspot.getName());
				history.setScenicspotid(scenicspot.getId());
				history.setUser((String) session.getAttribute("mingzi"));
				history.setUserid((Integer) session.getAttribute("id"));
				
				historydao.insert(history);
			}else{
				historyall.get(0).setAddtime(sdf.format(d));
				historydao.updateByPrimaryKey(historyall.get(0));
			}
			
		}

		// 将查询到的景点保存到request中

		request.setAttribute("scenicspot", scenicspot);

	}

	// 定义Scenicspot详情方法

	public Map scenicspotdetailsjson(int id) {

		// 输入日志信息，表名当前执行方法为ScenicspotController.scenicspotdetailsjson

		logger.debug("ScenicspotController.scenicspotdetailsjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 传入页面所传入的景点id使用scenicspotdao的selectByPrimaryKey方法进行查询

		Scenicspot scenicspot = scenicspotdao.selectByPrimaryKey(id);

		// 将查询到的景点保存到result中

		result.put("scenicspot", scenicspot);
		
		
		// 返回查询结果resultmap

		return result;

	}

	// 上传文件图片等

	public String uploadUtile(MultipartFile file, HttpServletRequest request) throws IOException {

		// 根据当前时间生成时间字符串

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
