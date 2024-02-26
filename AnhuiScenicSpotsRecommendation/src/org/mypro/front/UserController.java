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

import org.mypro.service.UserServiceImp;

import org.mypro.entity.User;

import org.mypro.entity.UserExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping(value = "/")

public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private UserServiceImp userservice;

	// 定义方法adduser,响应页面adduser请求

	@RequestMapping(value = "adduser")

	public String adduser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String backurl) {

		// 输出日志，当前执行方法为adduser

		logger.debug("UserController.adduser ......");

		userservice.adduser(request, session);

		// 若返回地址不等于空且不等于adduser.action，则将该请求跳转到backurl地址，再次执行方法

		if (backurl != null && backurl.indexOf("adduser.action") == -1) {
			// 跳转到backurl中

			return "forward:/" + backurl;
		}
		// 返回adduser页面

		return "adduser";

	}

	// 定义adduseract方法

	@RequestMapping(value = "adduseract")

	public String adduseract(HttpServletRequest request, HttpSession session, HttpServletResponse response, User user,
			String backurl) throws IOException {

		// 输出当前方法日志，表示正在执行UserController.adduseract方法

		logger.debug("UserController.adduseract ......");

		userservice.adduseract(request, session, user); // 如果backurl不等于空，且backurl与本方法不同，则返回到backurl方法中

		if (backurl != null && backurl.indexOf("adduseract.action") == -1) { // 返回到backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回adduser方法

		return "forward:/usermanage.action";

	}

	@ResponseBody
	// 定义adduseractjson方法

	@RequestMapping(value = "adduseractjson")

	public String adduseractjson(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestBody User user) throws IOException {

		// 输出当前方法日志，表示正在执行UserController.adduseractjson方法

		logger.debug("UserController.adduseractjson ......");

		// 调用服务层adduseract方法

		userservice.adduseractjson(user);

		// 返回adduser方法

		return "success";

	}

	// 定义usermanage方法响应页面请求

	@RequestMapping(value = "usermanage")

	public String usermanage(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行UserController.usermanage

		logger.debug("UserController.usermanage ......");

		userservice.usermanage(request, session); // 如果backurl不为空，且backurl与当前方法不同，则返回backurl地址所在的方法

		if (backurl != null && backurl.indexOf("usermanage.action") == -1) { // 返回backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回到usermanage页面

		return "usermanage";

	}

	// 定义 userview方法

	@RequestMapping(value = "userview")

	public String userview(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行UserController.userview方法

		logger.debug("UserController.userview ......");

		userservice.userview(request, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("userview.action") == -1) { // 返回backur所在的方法

			return "forward:/" + backurl;
		}

		// 返回userview页面

		return "userview";

	}

	// 定义 updateuser方法

	@RequestMapping(value = "updateuser")

	public String updateuser(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志UserController.updateuser，表示正在执行该方法

		logger.debug("UserController.updateuser ......");

		userservice.updateuser(request, session, id); // 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("updateuser.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回updateuser页面

		return "updateuser";

	}

	// 定义updateuseract处理用户修改

	@RequestMapping(value = "updateuseract")

	public String updateuseract(HttpServletRequest request, HttpServletResponse response, User user,
			HttpSession session, String backurl) throws IOException {

		// 输出日志，表示正在执行当前方法UserController.updateuseract

		logger.debug("UserController.updateuseract ......");

		userservice.updateuseract(request, user, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("updateuseract.action") == -1) { // 返回backurl所对应方法

			return "forward:/" + backurl;
		}

		// 返回usermanage.action方法

		return "forward:/usermanage.action";

	}

	@ResponseBody
	// 定义updateuseractjson处理用户修改

	@RequestMapping(value = "updateuseractjson")

	public String updateuseractjson(HttpServletRequest request, HttpServletResponse response, @RequestBody User user,
			HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法UserController.updateuseractjson

		logger.debug("UserController.updateuseractjson ......");

		userservice.updateuseractjson(user);

		return "success";

	}

	// 定义deleteuser,处理删除用户

	@RequestMapping(value = "deleteuser")

	public String deleteuser(HttpServletRequest request, HttpServletResponse response, HttpSession session, int id,
			String backurl) {

		// 输出日志，表示当前正在执行UserController.deleteuser方法

		logger.debug("UserController.deleteuser ......");

		userservice.deleteuser(request, session, id);

		if (backurl != null && backurl.indexOf("deleteuser.action") == -1) {
			// 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回usermanage.action方法

		return "forward:/usermanage.action";

	}

	@ResponseBody
	// 定义deleteuserjson,处理删除用户

	@RequestMapping(value = "deleteuserjson")

	public String deleteuserjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id) {

		// 输出日志，表示当前正在执行UserController.deleteuser方法

		logger.debug("UserController.deleteuser ......");

		userservice.deleteuser(request, session, id);

		return "success";

	}

	// 定义searchuser方法，处理搜索操作

	@RequestMapping(value = "searchuser")

	public String searchuser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行UserController.searchuser

		logger.debug("UserController.searchuser ......");

		userservice.searchuser(request, session, search);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("searchuser.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回backurl所对应的方法

		return "searchuser";

	}

	@ResponseBody
	// 定义searchuserjson方法，处理搜索操作
	@RequestMapping(value = "searchuserjson")

	public Map searchuserjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行UserController.searchuserjson

		logger.debug("UserController.searchuserjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 获取返回结果

		result = userservice.searchuserjson(search); // 将结果以json返回

		return result;

	}

	// 定义UserpinglunMapper

	@RequestMapping(value = "userdetails")

	public String userdetails(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为UserController.userdetails

		logger.debug("UserController.userdetails ......");

		userservice.userdetails(request, session, id);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("userdetails.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回userdetails

		return "userdetails";

	}

	@ResponseBody
	// 定义User详情方法

	@RequestMapping(value = "userdetailsjson")

	public Map userdetailsjson(HttpServletRequest request, HttpSession session, HttpServletResponse response, int id,
			String backurl) {

		// 输入日志信息，表名当前执行方法为UserController.userdetailsjson

		logger.debug("UserController.userdetailsjson ......");

		Map result = new HashMap();

		result = userservice.userdetailsjson(id);

		// 将结果以json返回

		return result;

	}

}
