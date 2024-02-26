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

import org.mypro.service.AdminServiceImp;

import org.mypro.entity.Admin;

import org.mypro.entity.AdminExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping(value = "/")

public class AdminController {

	private static final Log logger = LogFactory.getLog(AdminController.class);

	@Autowired
	private AdminServiceImp adminservice;

	// 定义方法addadmin,响应页面addadmin请求

	@RequestMapping(value = "test")

	public String test(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		return "addadmin";

	}

	@RequestMapping(value = "addadmin")

	public String addadmin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String backurl) {

		// 输出日志，当前执行方法为addadmin

		logger.debug("AdminController.addadmin ......");

		adminservice.addadmin(request, session);

		// 若返回地址不等于空且不等于addadmin.action，则将该请求跳转到backurl地址，再次执行方法

		if (backurl != null && backurl.indexOf("addadmin.action") == -1) {
			// 跳转到backurl中

			return "forward:/" + backurl;
		}
		// 返回addadmin页面

		return "addadmin";

	}

	// 定义addadminact方法

	@RequestMapping(value = "addadminact")

	public String addadminact(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Admin admin, String backurl) throws IOException {

		// 输出当前方法日志，表示正在执行AdminController.addadminact方法

		logger.debug("AdminController.addadminact ......");

		adminservice.addadminact(request, session, admin); // 如果backurl不等于空，且backurl与本方法不同，则返回到backurl方法中

		if (backurl != null && backurl.indexOf("addadminact.action") == -1) { // 返回到backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回addadmin方法

		return "forward:/adminmanage.action";

	}

	@ResponseBody
	// 定义addadminactjson方法

	@RequestMapping(value = "addadminactjson")

	public String addadminactjson(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestBody Admin admin) throws IOException {

		// 输出当前方法日志，表示正在执行AdminController.addadminactjson方法

		logger.debug("AdminController.addadminactjson ......");

		// 调用服务层addadminact方法

		adminservice.addadminactjson(admin);

		// 返回addadmin方法

		return "success";

	}

	// 定义adminmanage方法响应页面请求

	@RequestMapping(value = "adminmanage")

	public String adminmanage(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行AdminController.adminmanage

		logger.debug("AdminController.adminmanage ......");

		adminservice.adminmanage(request, session); // 如果backurl不为空，且backurl与当前方法不同，则返回backurl地址所在的方法

		if (backurl != null && backurl.indexOf("adminmanage.action") == -1) { // 返回backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回到adminmanage页面

		return "adminmanage";

	}

	// 定义 adminview方法

	@RequestMapping(value = "adminview")

	public String adminview(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行AdminController.adminview方法

		logger.debug("AdminController.adminview ......");

		adminservice.adminview(request, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("adminview.action") == -1) { // 返回backur所在的方法

			return "forward:/" + backurl;
		}

		// 返回adminview页面

		return "adminview";

	}

	// 定义 updateadmin方法

	@RequestMapping(value = "updateadmin")

	public String updateadmin(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志AdminController.updateadmin，表示正在执行该方法

		logger.debug("AdminController.updateadmin ......");

		adminservice.updateadmin(request, session, id); // 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("updateadmin.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回updateadmin页面

		return "updateadmin";

	}

	// 定义updateadminact处理管理员修改

	@RequestMapping(value = "updateadminact")

	public String updateadminact(HttpServletRequest request, HttpServletResponse response, Admin admin,
			HttpSession session, String backurl) throws IOException {

		// 输出日志，表示正在执行当前方法AdminController.updateadminact

		logger.debug("AdminController.updateadminact ......");

		adminservice.updateadminact(request, admin, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("updateadminact.action") == -1) { // 返回backurl所对应方法

			return "forward:/" + backurl;
		}

		// 返回adminmanage.action方法

		return "forward:/adminmanage.action";

	}

	@ResponseBody
	// 定义updateadminactjson处理管理员修改

	@RequestMapping(value = "updateadminactjson")

	public String updateadminactjson(HttpServletRequest request, HttpServletResponse response, @RequestBody Admin admin,
			HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法AdminController.updateadminactjson

		logger.debug("AdminController.updateadminactjson ......");

		adminservice.updateadminactjson(admin);

		return "success";

	}

	// 定义deleteadmin,处理删除管理员

	@RequestMapping(value = "deleteadmin")

	public String deleteadmin(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志，表示当前正在执行AdminController.deleteadmin方法

		logger.debug("AdminController.deleteadmin ......");

		adminservice.deleteadmin(request, session, id);

		if (backurl != null && backurl.indexOf("deleteadmin.action") == -1) {
			// 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回adminmanage.action方法

		return "forward:/adminmanage.action";

	}

	@ResponseBody
	// 定义deleteadminjson,处理删除管理员

	@RequestMapping(value = "deleteadminjson")

	public String deleteadminjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id) {

		// 输出日志，表示当前正在执行AdminController.deleteadmin方法

		logger.debug("AdminController.deleteadmin ......");

		adminservice.deleteadmin(request, session, id);

		return "success";

	}

	// 定义searchadmin方法，处理搜索操作

	@RequestMapping(value = "searchadmin")

	public String searchadmin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行AdminController.searchadmin

		logger.debug("AdminController.searchadmin ......");

		adminservice.searchadmin(request, session, search);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("searchadmin.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回backurl所对应的方法

		return "searchadmin";

	}

	@ResponseBody
	// 定义searchadminjson方法，处理搜索操作
	@RequestMapping(value = "searchadminjson")

	public Map searchadminjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行AdminController.searchadminjson

		logger.debug("AdminController.searchadminjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 获取返回结果

		result = adminservice.searchadminjson(search); // 将结果以json返回

		return result;

	}

	// 定义AdminpinglunMapper

	@RequestMapping(value = "admindetails")

	public String admindetails(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为AdminController.admindetails

		logger.debug("AdminController.admindetails ......");

		adminservice.admindetails(request, session, id);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("admindetails.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回admindetails

		return "admindetails";

	}

	@ResponseBody
	// 定义Admin详情方法

	@RequestMapping(value = "admindetailsjson")

	public Map admindetailsjson(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为AdminController.admindetailsjson

		logger.debug("AdminController.admindetailsjson ......");

		Map result = new HashMap();

		result = adminservice.admindetailsjson(id);

		// 将结果以json返回

		return result;

	}

}
