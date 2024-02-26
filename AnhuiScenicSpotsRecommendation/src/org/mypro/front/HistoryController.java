package org.mypro.front;

import java.io.File;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

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

import org.mypro.service.HistoryServiceImp;

import org.mypro.entity.History;

import org.mypro.entity.HistoryExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping(value = "/")

public class HistoryController {

	private static final Log logger = LogFactory.getLog(HistoryController.class);

	@Autowired
	private HistoryServiceImp historyservice;

	// 定义方法addhistory,响应页面addhistory请求

	@RequestMapping(value = "addhistory")

	public String addhistory(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String backurl) {

		// 输出日志，当前执行方法为addhistory

		logger.debug("HistoryController.addhistory ......");

		historyservice.addhistory(request, session);

		// 若返回地址不等于空且不等于addhistory.action，则将该请求跳转到backurl地址，再次执行方法

		if (backurl != null && backurl.indexOf("addhistory.action") == -1) {
			// 跳转到backurl中

			return "forward:/" + backurl;
		}
		// 返回addhistory页面

		return "addhistory";

	}

	// 定义addhistoryact方法

	@RequestMapping(value = "addhistoryact")

	public String addhistoryact(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			History history, String backurl) throws IOException {

		// 输出当前方法日志，表示正在执行HistoryController.addhistoryact方法

		logger.debug("HistoryController.addhistoryact ......");

		historyservice.addhistoryact(request, session, history); // 如果backurl不等于空，且backurl与本方法不同，则返回到backurl方法中

		if (backurl != null && backurl.indexOf("addhistoryact.action") == -1) { // 返回到backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回addhistory方法

		return "forward:/historymanage.action";

	}

	@ResponseBody
	// 定义addhistoryactjson方法

	@RequestMapping(value = "addhistoryactjson")

	public String addhistoryactjson(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestBody History history) throws IOException {

		// 输出当前方法日志，表示正在执行HistoryController.addhistoryactjson方法

		logger.debug("HistoryController.addhistoryactjson ......");

		// 调用服务层addhistoryact方法

		historyservice.addhistoryactjson(history);

		// 返回addhistory方法

		return "success";

	}
	
	
	// 定义userhistorymanage方法响应页面请求
	@RequestMapping(value = "userhistorymanage")
	public String userhistorymanage(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		// 输出日志，表示当前正在执行HistoryController.historymanage

		logger.debug("HistoryController.historymanage ......");

		historyservice.userhistorymanage(request, session); 

		return "userhistorymanage";

	}
	
	// 定义historymanage方法响应页面请求

	@RequestMapping(value = "historymanage")

	public String historymanage(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行HistoryController.historymanage

		logger.debug("HistoryController.historymanage ......");

		historyservice.historymanage(request, session); // 如果backurl不为空，且backurl与当前方法不同，则返回backurl地址所在的方法

		if (backurl != null && backurl.indexOf("historymanage.action") == -1) { // 返回backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回到historymanage页面

		return "historymanage";

	}

	// 定义 historyview方法

	@RequestMapping(value = "historyview")

	public String historyview(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行HistoryController.historyview方法

		logger.debug("HistoryController.historyview ......");

		historyservice.historyview(request, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("historyview.action") == -1) { // 返回backur所在的方法

			return "forward:/" + backurl;
		}

		// 返回historyview页面

		return "historyview";

	}

	// 定义 updatehistory方法

	@RequestMapping(value = "updatehistory")

	public String updatehistory(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志HistoryController.updatehistory，表示正在执行该方法

		logger.debug("HistoryController.updatehistory ......");

		historyservice.updatehistory(request, session, id); // 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("updatehistory.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回updatehistory页面

		return "updatehistory";

	}

	// 定义updatehistoryact处理访问历史修改

	@RequestMapping(value = "updatehistoryact")

	public String updatehistoryact(HttpServletRequest request, HttpServletResponse response, History history,
			HttpSession session, String backurl) throws IOException {

		// 输出日志，表示正在执行当前方法HistoryController.updatehistoryact

		logger.debug("HistoryController.updatehistoryact ......");

		historyservice.updatehistoryact(request, history, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("updatehistoryact.action") == -1) { // 返回backurl所对应方法

			return "forward:/" + backurl;
		}

		// 返回historymanage.action方法

		return "forward:/historymanage.action";

	}

	@ResponseBody
	// 定义updatehistoryactjson处理访问历史修改

	@RequestMapping(value = "updatehistoryactjson")

	public String updatehistoryactjson(HttpServletRequest request, HttpServletResponse response,
			@RequestBody History history, HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法HistoryController.updatehistoryactjson

		logger.debug("HistoryController.updatehistoryactjson ......");

		historyservice.updatehistoryactjson(history);

		return "success";

	}

	// 定义deletehistory,处理删除访问历史

	@RequestMapping(value = "deletehistory")

	public String deletehistory(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志，表示当前正在执行HistoryController.deletehistory方法

		logger.debug("HistoryController.deletehistory ......");

		historyservice.deletehistory(request, session, id);

		if (backurl != null && backurl.indexOf("deletehistory.action") == -1) {
			// 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回historymanage.action方法

		return "forward:/historymanage.action";

	}

	@ResponseBody
	// 定义deletehistoryjson,处理删除访问历史

	@RequestMapping(value = "deletehistoryjson")

	public String deletehistoryjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id) {

		// 输出日志，表示当前正在执行HistoryController.deletehistory方法

		logger.debug("HistoryController.deletehistory ......");

		historyservice.deletehistory(request, session, id);

		return "success";

	}

	// 定义searchhistory方法，处理搜索操作

	@RequestMapping(value = "searchhistory")

	public String searchhistory(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行HistoryController.searchhistory

		logger.debug("HistoryController.searchhistory ......");

		historyservice.searchhistory(request, session, search);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("searchhistory.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回backurl所对应的方法

		return "searchhistory";

	}

	@ResponseBody
	// 定义searchhistoryjson方法，处理搜索操作
	@RequestMapping(value = "searchhistoryjson")

	public Map searchhistoryjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行HistoryController.searchhistoryjson

		logger.debug("HistoryController.searchhistoryjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 获取返回结果

		result = historyservice.searchhistoryjson(search); // 将结果以json返回

		return result;

	}

	// 定义HistorypinglunMapper

	@RequestMapping(value = "historydetails")

	public String historydetails(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为HistoryController.historydetails

		logger.debug("HistoryController.historydetails ......");

		historyservice.historydetails(request, session, id);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("historydetails.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回historydetails

		return "historydetails";

	}

	@ResponseBody
	// 定义History详情方法

	@RequestMapping(value = "historydetailsjson")

	public Map historydetailsjson(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为HistoryController.historydetailsjson

		logger.debug("HistoryController.historydetailsjson ......");

		Map result = new HashMap();

		result = historyservice.historydetailsjson(id);

		// 将结果以json返回

		return result;

	}

}
