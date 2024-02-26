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

import org.mypro.dao.AdminMapper;

import org.mypro.entity.Admin;

import org.mypro.entity.AdminExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Service

public class AdminService implements AdminServiceImp {

	private static final Log logger = LogFactory.getLog(AdminService.class);

	@Autowired
	private AdminMapper admindao;

	// 定义方法addadmin,响应控制层addadmin请求

	public void addadmin(HttpServletRequest request, HttpSession session) {

		// 输出日志，当前执行方法为addadmin

		logger.debug("AdminService.addadmin ......");

	}

	// 定义addadminact方法

	public void addadminact(HttpServletRequest request, HttpSession session, Admin admin) throws IOException {

		// 输出当前方法日志，表示正在执行AdminService.addadminact方法

		logger.debug("AdminService.addadminact ......");

		// 使用admindao的insert方法将管理员添加到数据库中

		admindao.insert(admin);

		// 将添加管理员信息添加到request中用message参数进行保存

		request.setAttribute("message", "添加管理员成功");

	} // 定义addadminactjson方法

	public void addadminactjson(Admin admin) throws IOException {

		// 输出当前方法日志，表示正在执行AdminService.addadminactjson方法

		logger.debug("AdminService.addadminactjson ......");

		// 使用admindao的insert方法将管理员添加到数据库中

		admindao.insert(admin);

	}

	// 定义adminmanage方法响应页面请求

	public void adminmanage(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行AdminService.adminmanage

		logger.debug("AdminService.adminmanage ......");

		// 实例化AdminExample类为example

		AdminExample example = new AdminExample();

		// 使用 admindao.selectByExample(example)方法获取所有的管理员数据

		List adminall = admindao.selectByExample(example);

		// 将所有的管理员数据保存到request中的adminall参数里

		request.setAttribute("adminall", adminall);

	}

	// 定义 adminview方法

	public void adminview(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行AdminService.adminview方法

		logger.debug("AdminService.adminview ......");

		// 实例化AdminExample类

		AdminExample example = new AdminExample();

		// 使用admindao的selectByExample方法查询管理员信息

		List adminall = admindao.selectByExample(example);

		// 将查询的adminall保存到request中，记录为参数adminall

		request.setAttribute("adminall", adminall);

	}

	// 定义 updateadmin方法

	public void updateadmin(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志AdminController.updateadmin，表示正在执行该方法

		logger.debug("AdminController.updateadmin ......");

		// 定义管理员为biaoming，使用 admindao的selectByPrimaryKey方法通过id查询数据

		Admin admin = admindao.selectByPrimaryKey(id);

		// 将查询的管理员保存到request中

		request.setAttribute("admin", admin);

	}

	// 定义updateadminact处理管理员修改

	public void updateadminact(HttpServletRequest request, Admin admin, HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法AdminController.updateadminact

		logger.debug("AdminController.updateadminact ......");

		// 执行admindao的updateByPrimaryKeySelective方法，将传入的管理员数据同步到数据库中

		admindao.updateByPrimaryKeySelective(admin);

		// 将修改成功信息保存到request中

		request.setAttribute("message", "修改管理员信息成功");

	} // 定义updateadminactjson处理管理员修改

	public void updateadminactjson(Admin admin) throws IOException {

		// 输出日志，表示正在执行当前方法AdminController.updateadminactjson

		logger.debug("AdminController.updateadminactjson ......");

		// 执行admindao的updateByPrimaryKeySelective方法，将传入的管理员数据同步到数据库中

		admindao.updateByPrimaryKeySelective(admin);

	}

	// 定义deleteadmin,处理删除管理员

	public void deleteadmin(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志，表示当前正在执行AdminService.deleteadmin方法

		logger.debug("AdminService.deleteadmin ......");

		// 根据id调用admindao的deleteByPrimaryKey方法，删除管理员

		admindao.deleteByPrimaryKey(id);

		request.setAttribute("message", "删除管理员成功");

	}

	// 定义searchadmin方法，处理搜索操作

	public void searchadmin(HttpServletRequest request, HttpSession session, String search) {

		// 输出日志，表示当前正在执行AdminService.searchadmin

		logger.debug("AdminService.searchadmin ......");

		// 实例化 AdminExample

		AdminExample example = new AdminExample();

		// 实例化 AdminExample的Criteria内部类

		AdminExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andUsernameLike("%" + search + "%");

		}

		// 查询管理员结果

		List adminall = admindao.selectByExample(example);

		// 将查询到的管理员 保存到request中进行保存

		request.setAttribute("adminall", adminall);

	}

	// 定义searchadminjson方法，处理搜索操作

	public Map searchadminjson(String search) {

		// 输出日志，表示当前正在执行AdminService.searchadminjson

		logger.debug("AdminService.searchadminjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 实例化 AdminExample

		AdminExample example = new AdminExample();

		// 实例化 AdminExample的Criteria内部类

		AdminExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andUsernameLike("%" + search + "%");

		}

		// 查询管理员结果

		List adminall = admindao.selectByExample(example);

		// 将查询到的管理员 保存到request中进行保存

		result.put("adminall", adminall);

		// 返回查询结果resultmap

		return result;

	}

	// 定义AdminpinglunMapper

	public void admindetails(HttpServletRequest request, HttpSession session, int id) {

		// 输入日志信息，表名当前执行方法为AdminController.admindetails

		logger.debug("AdminController.admindetails ......");

		// 传入页面所传入的管理员id使用admindao的selectByPrimaryKey方法进行查询

		Admin admin = admindao.selectByPrimaryKey(id);

		// 将查询到的管理员保存到request中

		request.setAttribute("admin", admin);

	}

	// 定义Admin详情方法

	public Map admindetailsjson(int id) {

		// 输入日志信息，表名当前执行方法为AdminController.admindetailsjson

		logger.debug("AdminController.admindetailsjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 传入页面所传入的管理员id使用admindao的selectByPrimaryKey方法进行查询

		Admin admin = admindao.selectByPrimaryKey(id);

		// 将查询到的管理员保存到result中

		result.put("admin", admin);

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
