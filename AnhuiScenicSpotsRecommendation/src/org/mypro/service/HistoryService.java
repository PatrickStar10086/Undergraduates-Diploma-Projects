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

import org.mypro.entity.History;

import org.mypro.entity.HistoryExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Service

public class HistoryService implements HistoryServiceImp {

	private static final Log logger = LogFactory.getLog(HistoryService.class);

	@Autowired
	private HistoryMapper historydao;

	// 定义方法addhistory,响应控制层addhistory请求

	public void addhistory(HttpServletRequest request, HttpSession session) {

		// 输出日志，当前执行方法为addhistory

		logger.debug("HistoryService.addhistory ......");

	}

	// 定义addhistoryact方法

	public void addhistoryact(HttpServletRequest request, HttpSession session, History history) throws IOException {

		// 输出当前方法日志，表示正在执行HistoryService.addhistoryact方法

		logger.debug("HistoryService.addhistoryact ......");

		// 使用historydao的insert方法将访问历史添加到数据库中

		historydao.insert(history);

		// 将添加访问历史信息添加到request中用message参数进行保存

		request.setAttribute("message", "添加访问历史成功");

	} // 定义addhistoryactjson方法

	public void addhistoryactjson(History history) throws IOException {

		// 输出当前方法日志，表示正在执行HistoryService.addhistoryactjson方法

		logger.debug("HistoryService.addhistoryactjson ......");

		// 使用historydao的insert方法将访问历史添加到数据库中

		historydao.insert(history);

	}

	

	public void userhistorymanage(HttpServletRequest request, HttpSession session) {
		// 输出日志，表示当前正在执行HistoryService.userhistorymanage
		logger.debug("HistoryService.userhistorymanage ......");

		// 实例化HistoryExample类为example
		HistoryExample example = new HistoryExample();
		HistoryExample.Criteria criteria = example.createCriteria();
		
		criteria.andUseridEqualTo((Integer) session.getAttribute("id"));

		// 使用 historydao.selectByExample(example)方法获取所有的访问历史数据
		List historyall = historydao.selectByExample(example);

		// 将所有的访问历史数据保存到request中的historyall参数里
		request.setAttribute("historyall", historyall);

	}

	
	// 定义historymanage方法响应页面请求

	public void historymanage(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行HistoryService.historymanage

		logger.debug("HistoryService.historymanage ......");

		// 实例化HistoryExample类为example

		HistoryExample example = new HistoryExample();

		// 使用 historydao.selectByExample(example)方法获取所有的访问历史数据

		List historyall = historydao.selectByExample(example);

		// 将所有的访问历史数据保存到request中的historyall参数里

		request.setAttribute("historyall", historyall);

	}

	// 定义 historyview方法

	public void historyview(HttpServletRequest request, HttpSession session) {

		// 输出日志，表示当前正在执行HistoryService.historyview方法

		logger.debug("HistoryService.historyview ......");

		// 实例化HistoryExample类

		HistoryExample example = new HistoryExample();

		// 使用historydao的selectByExample方法查询访问历史信息

		List historyall = historydao.selectByExample(example);

		// 将查询的historyall保存到request中，记录为参数historyall

		request.setAttribute("historyall", historyall);

	}

	// 定义 updatehistory方法

	public void updatehistory(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志HistoryController.updatehistory，表示正在执行该方法

		logger.debug("HistoryController.updatehistory ......");

		// 定义访问历史为biaoming，使用 historydao的selectByPrimaryKey方法通过id查询数据

		History history = historydao.selectByPrimaryKey(id);

		// 将查询的访问历史保存到request中

		request.setAttribute("history", history);

	}

	// 定义updatehistoryact处理访问历史修改

	public void updatehistoryact(HttpServletRequest request, History history, HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法HistoryController.updatehistoryact

		logger.debug("HistoryController.updatehistoryact ......");

		// 执行historydao的updateByPrimaryKeySelective方法，将传入的访问历史数据同步到数据库中

		historydao.updateByPrimaryKeySelective(history);

		// 将修改成功信息保存到request中

		request.setAttribute("message", "修改访问历史信息成功");

	} // 定义updatehistoryactjson处理访问历史修改

	public void updatehistoryactjson(History history) throws IOException {

		// 输出日志，表示正在执行当前方法HistoryController.updatehistoryactjson

		logger.debug("HistoryController.updatehistoryactjson ......");

		// 执行historydao的updateByPrimaryKeySelective方法，将传入的访问历史数据同步到数据库中

		historydao.updateByPrimaryKeySelective(history);

	}

	// 定义deletehistory,处理删除访问历史

	public void deletehistory(HttpServletRequest request, HttpSession session, int id) {

		// 输出日志，表示当前正在执行HistoryService.deletehistory方法

		logger.debug("HistoryService.deletehistory ......");

		// 根据id调用historydao的deleteByPrimaryKey方法，删除访问历史

		historydao.deleteByPrimaryKey(id);

		request.setAttribute("message", "删除访问历史成功");

	}

	// 定义searchhistory方法，处理搜索操作

	public void searchhistory(HttpServletRequest request, HttpSession session, String search) {

		// 输出日志，表示当前正在执行HistoryService.searchhistory

		logger.debug("HistoryService.searchhistory ......");

		// 实例化 HistoryExample

		HistoryExample example = new HistoryExample();

		// 实例化 HistoryExample的Criteria内部类

		HistoryExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andScenicspotLike("%" + search + "%");

		}

		// 查询访问历史结果

		List historyall = historydao.selectByExample(example);

		// 将查询到的访问历史 保存到request中进行保存

		request.setAttribute("historyall", historyall);

	}

	// 定义searchhistoryjson方法，处理搜索操作

	public Map searchhistoryjson(String search) {

		// 输出日志，表示当前正在执行HistoryService.searchhistoryjson

		logger.debug("HistoryService.searchhistoryjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 实例化 HistoryExample

		HistoryExample example = new HistoryExample();

		// 实例化 HistoryExample的Criteria内部类

		HistoryExample.Criteria criteria = example.createCriteria();

		// 如果搜索内容不等于空

		if (search != null) {

			// 将搜索内容通过like关键字传到数据库中进行搜索

			criteria.andScenicspotLike("%" + search + "%");

		}

		// 查询访问历史结果

		List historyall = historydao.selectByExample(example);

		// 将查询到的访问历史 保存到request中进行保存

		result.put("historyall", historyall);

		// 返回查询结果resultmap

		return result;

	}

	// 定义HistorypinglunMapper

	public void historydetails(HttpServletRequest request, HttpSession session, int id) {

		// 输入日志信息，表名当前执行方法为HistoryController.historydetails

		logger.debug("HistoryController.historydetails ......");

		// 传入页面所传入的访问历史id使用historydao的selectByPrimaryKey方法进行查询

		History history = historydao.selectByPrimaryKey(id);

		// 将查询到的访问历史保存到request中

		request.setAttribute("history", history);

	}

	// 定义History详情方法

	public Map historydetailsjson(int id) {

		// 输入日志信息，表名当前执行方法为HistoryController.historydetailsjson

		logger.debug("HistoryController.historydetailsjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 传入页面所传入的访问历史id使用historydao的selectByPrimaryKey方法进行查询

		History history = historydao.selectByPrimaryKey(id);

		// 将查询到的访问历史保存到result中

		result.put("history", history);

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
