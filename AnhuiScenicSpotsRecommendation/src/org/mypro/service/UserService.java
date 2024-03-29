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

import org.mypro.dao.UserMapper;

import org.mypro.entity.User;

import org.mypro.entity.UserExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Service

public class UserService implements UserServiceImp {

	private static final Log logger = LogFactory.getLog(UserService.class);

	@Autowired
	private UserMapper userdao;

	// 定义方法adduser,响应控制层adduser请求

	public void adduser(HttpServletRequest request, HttpSession session) {

		// 输出日志，当前执行方法为adduser

		logger.debug("UserService.adduser ......");

	}

	// 定义adduseract方法

	public void adduseract(HttpServletRequest request, HttpSession session, User user) throws IOException {

		// 输出当前方法日志，表示正在执行UserService.adduseract方法

		logger.debug("UserService.adduseract ......");

		// 使用userdao的insert方法将用户添加到数据库中

		userdao.insert(user);

		// 将添加用户信息添加到request中用message参数进行保存

		request.setAttribute("message", "添加用户成功");

	} // 定义adduseractjson方法

	public void adduseractjson(User user) throws IOException {

		// 输出当前方法日志，表示正在执行UserService.adduseractjson方法

		logger.debug("UserService.adduseractjson ......");

		// 使用userdao的insert方法将用户添加到数据库中

		userdao.insert(user);

	}

	// 定义usermanage方法响应页面请求

	public void usermanage(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行UserService.usermanage

		logger.debug("UserService.usermanage ......");

		// 实例化UserExample类为example

		UserExample example = new UserExample();

		// 使用 userdao.selectByExample(example)方法获取所有的用户数据

		List userall = userdao.selectByExample(example);

		// 将所有的用户数据保存到request中的userall参数里

		request.setAttribute("userall", userall);

	}

	// 定义 userview方法

	public void userview(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行UserService.userview方法

		logger.debug("UserService.userview ......");

		// 实例化UserExample类

		UserExample example = new UserExample();

		// 使用userdao的selectByExample方法查询用户信息

		List userall = userdao.selectByExample(example);

		// 将查询的userall保存到request中，记录为参数userall

		request.setAttribute("userall", userall);

	}

	// 定义 updateuser方法

	public void updateuser(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志UserController.updateuser，表示正在执行该方法

		logger.debug("UserController.updateuser ......");

		// 定义用户为biaoming，使用 userdao的selectByPrimaryKey方法通过id查询数据

		User user = userdao.selectByPrimaryKey(id);

		// 将查询的用户保存到request中

		request.setAttribute("user", user);

	}

	// 定义updateuseract处理用户修改

	public void updateuseract(HttpServletRequest request, User user, HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法UserController.updateuseract

		logger.debug("UserController.updateuseract ......");

		// 执行userdao的updateByPrimaryKeySelective方法，将传入的用户数据同步到数据库中

		userdao.updateByPrimaryKeySelective(user);

		// 将修改成功信息保存到request中

		request.setAttribute("message", "修改用户信息成功");

	} // 定义updateuseractjson处理用户修改

	public void updateuseractjson(User user) throws IOException {

		// 输出日志，表示正在执行当前方法UserController.updateuseractjson

		logger.debug("UserController.updateuseractjson ......");

		// 执行userdao的updateByPrimaryKeySelective方法，将传入的用户数据同步到数据库中

		userdao.updateByPrimaryKeySelective(user);

	}

	// 定义deleteuser,处理删除用户

	public void deleteuser(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志，表示当前正在执行UserService.deleteuser方法

		logger.debug("UserService.deleteuser ......");

		// 根据id调用userdao的deleteByPrimaryKey方法，删除用户

		userdao.deleteByPrimaryKey(id);

		request.setAttribute("message", "删除用户成功");

	}

	// 定义searchuser方法，处理搜索操作

	public void searchuser(HttpServletRequest request, HttpSession session, String search) {

		// 输出日志，表示当前正在执行UserService.searchuser

		logger.debug("UserService.searchuser ......");

		// 实例化 UserExample

		UserExample example = new UserExample();

		// 实例化 UserExample的Criteria内部类

		UserExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andNameLike("%" + search + "%");

		}

		// 查询用户结果

		List userall = userdao.selectByExample(example);

		// 将查询到的用户 保存到request中进行保存

		request.setAttribute("userall", userall);

	}

	// 定义searchuserjson方法，处理搜索操作

	public Map searchuserjson(String search) {

		// 输出日志，表示当前正在执行UserService.searchuserjson

		logger.debug("UserService.searchuserjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 实例化 UserExample

		UserExample example = new UserExample();

		// 实例化 UserExample的Criteria内部类

		UserExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andNameLike("%" + search + "%");

		}

		// 查询用户结果

		List userall = userdao.selectByExample(example);

		// 将查询到的用户 保存到request中进行保存

		result.put("userall", userall);

		// 返回查询结果resultmap

		return result;

	}

	// 定义UserpinglunMapper

	public void userdetails(HttpServletRequest request, HttpSession session, int id) {

		// 输入日志信息，表名当前执行方法为UserController.userdetails

		logger.debug("UserController.userdetails ......");

		// 传入页面所传入的用户id使用userdao的selectByPrimaryKey方法进行查询

		User user = userdao.selectByPrimaryKey(id);

		// 将查询到的用户保存到request中

		request.setAttribute("user", user);

	}

	// 定义User详情方法

	public Map userdetailsjson(int id) {

		// 输入日志信息，表名当前执行方法为UserController.userdetailsjson

		logger.debug("UserController.userdetailsjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 传入页面所传入的用户id使用userdao的selectByPrimaryKey方法进行查询

		User user = userdao.selectByPrimaryKey(id);

		// 将查询到的用户保存到result中

		result.put("user", user);

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
