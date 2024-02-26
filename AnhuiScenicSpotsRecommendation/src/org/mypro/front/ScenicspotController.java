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

import org.mypro.service.ScenicspotServiceImp;

import org.mypro.entity.Scenicspot;

import org.mypro.entity.ScenicspotExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping(value = "/")

public class ScenicspotController {

	private static final Log logger = LogFactory.getLog(ScenicspotController.class);

	@Autowired
	private ScenicspotServiceImp scenicspotservice;

	// 定义方法addscenicspot,响应页面addscenicspot请求

	@RequestMapping(value = "addscenicspot")

	public String addscenicspot(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String backurl) {

		// 输出日志，当前执行方法为addscenicspot

		logger.debug("ScenicspotController.addscenicspot ......");

		scenicspotservice.addscenicspot(request, session);

		// 若返回地址不等于空且不等于addscenicspot.action，则将该请求跳转到backurl地址，再次执行方法

		if (backurl != null && backurl.indexOf("addscenicspot.action") == -1) {
			// 跳转到backurl中
			
			return "forward:/" + backurl;
		}
		// 返回addscenicspot页面

		return "addscenicspot";

	}

	// 定义addscenicspotact方法

	@RequestMapping(value = "addscenicspotact")

	public String addscenicspotact(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			Scenicspot scenicspot, String backurl) throws IOException {

		// 输出当前方法日志，表示正在执行ScenicspotController.addscenicspotact方法

		logger.debug("ScenicspotController.addscenicspotact ......");

		scenicspotservice.addscenicspotact(request, session, scenicspot); // 如果backurl不等于空，且backurl与本方法不同，则返回到backurl方法中

		if (backurl != null && backurl.indexOf("addscenicspotact.action") == -1) { // 返回到backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回addscenicspot方法

		return "forward:/scenicspotmanage.action";

	}

	@ResponseBody
	// 定义addscenicspotactjson方法

	@RequestMapping(value = "addscenicspotactjson")

	public String addscenicspotactjson(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestBody Scenicspot scenicspot) throws IOException {

		// 输出当前方法日志，表示正在执行ScenicspotController.addscenicspotactjson方法

		logger.debug("ScenicspotController.addscenicspotactjson ......");

		// 调用服务层addscenicspotact方法

		scenicspotservice.addscenicspotactjson(scenicspot);

		// 返回addscenicspot方法

		return "success";

	}

	// 定义scenicspotmanage方法响应页面请求

	@RequestMapping(value = "scenicspotmanage")

	public String scenicspotmanage(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行ScenicspotController.scenicspotmanage

		logger.debug("ScenicspotController.scenicspotmanage ......");

		scenicspotservice.scenicspotmanage(request, session); // 如果backurl不为空，且backurl与当前方法不同，则返回backurl地址所在的方法

		if (backurl != null && backurl.indexOf("scenicspotmanage.action") == -1) { // 返回backurl所在的方法

			return "forward:/" + backurl;
		}

		// 返回到scenicspotmanage页面

		return "scenicspotmanage";

	}

	// 定义 scenicspotview方法

	@RequestMapping(value = "scenicspotview")

	public String scenicspotview(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String backurl) {

		// 输出日志，表示当前正在执行ScenicspotController.scenicspotview方法

		logger.debug("ScenicspotController.scenicspotview ......");

		scenicspotservice.scenicspotview(request, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("scenicspotview.action") == -1) { // 返回backur所在的方法

			return "forward:/" + backurl;
		}

		// 返回scenicspotview页面

		return "scenicspotview";

	}

	// 定义 updatescenicspot方法

	@RequestMapping(value = "updatescenicspot")

	public String updatescenicspot(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id, String backurl) {

		// 输出日志ScenicspotController.updatescenicspot，表示正在执行该方法

		logger.debug("ScenicspotController.updatescenicspot ......");

		scenicspotservice.updatescenicspot(request, session, id); // 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("updatescenicspot.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回updatescenicspot页面

		return "updatescenicspot";

	}

	// 定义updatescenicspotact处理景点修改

	@RequestMapping(value = "updatescenicspotact")

	public String updatescenicspotact(HttpServletRequest request, HttpServletResponse response, Scenicspot scenicspot,
			HttpSession session, String backurl) throws IOException {

		// 输出日志，表示正在执行当前方法ScenicspotController.updatescenicspotact

		logger.debug("ScenicspotController.updatescenicspotact ......");

		scenicspotservice.updatescenicspotact(request, scenicspot, session);

		// 如果backurl不等于null且backurl不等于当前方法名

		if (backurl != null && backurl.indexOf("updatescenicspotact.action") == -1) { // 返回backurl所对应方法

			return "forward:/" + backurl;
		}

		// 返回scenicspotmanage.action方法

		return "forward:/scenicspotmanage.action";

	}

	@ResponseBody
	// 定义updatescenicspotactjson处理景点修改

	@RequestMapping(value = "updatescenicspotactjson")

	public String updatescenicspotactjson(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Scenicspot scenicspot, HttpSession session) throws IOException {

		// 输出日志，表示正在执行当前方法ScenicspotController.updatescenicspotactjson

		logger.debug("ScenicspotController.updatescenicspotactjson ......");

		scenicspotservice.updatescenicspotactjson(scenicspot);

		return "success";

	}

	// 定义deletescenicspot,处理删除景点

	@RequestMapping(value = "deletescenicspot")

	public String deletescenicspot(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id, String backurl) {

		// 输出日志，表示当前正在执行ScenicspotController.deletescenicspot方法

		logger.debug("ScenicspotController.deletescenicspot ......");

		scenicspotservice.deletescenicspot(request, session, id);

		if (backurl != null && backurl.indexOf("deletescenicspot.action") == -1) {
			// 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回scenicspotmanage.action方法

		return "forward:/scenicspotmanage.action";

	}

	@ResponseBody
	// 定义deletescenicspotjson,处理删除景点

	@RequestMapping(value = "deletescenicspotjson")

	public String deletescenicspotjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			int id) {

		// 输出日志，表示当前正在执行ScenicspotController.deletescenicspot方法

		logger.debug("ScenicspotController.deletescenicspot ......");

		scenicspotservice.deletescenicspot(request, session, id);

		return "success";

	}

	// 定义searchscenicspot方法，处理搜索操作

	@RequestMapping(value = "searchscenicspot")

	public String searchscenicspot(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行ScenicspotController.searchscenicspot

		logger.debug("ScenicspotController.searchscenicspot ......");

		scenicspotservice.searchscenicspot(request, session, search);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("searchscenicspot.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回backurl所对应的方法

		return "searchscenicspot";

	}

	@ResponseBody
	// 定义searchscenicspotjson方法，处理搜索操作
	@RequestMapping(value = "searchscenicspotjson")

	public Map searchscenicspotjson(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String search, String backurl) {

		// 输出日志，表示当前正在执行ScenicspotController.searchscenicspotjson

		logger.debug("ScenicspotController.searchscenicspotjson ......");

		// 定义返回结果

		Map result = new HashMap();

		// 获取返回结果

		result = scenicspotservice.searchscenicspotjson(search); // 将结果以json返回

		return result;

	}

	// 定义ScenicspotpinglunMapper

	@RequestMapping(value = "scenicspotdetails")

	public String scenicspotdetails(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			int id, String backurl) {

		// 输入日志信息，表名当前执行方法为ScenicspotController.scenicspotdetails

		logger.debug("ScenicspotController.scenicspotdetails ......");

		scenicspotservice.scenicspotdetails(request, session, id);

		// 如果backurl不等于null且backurl不等于当前方法

		if (backurl != null && backurl.indexOf("scenicspotdetails.action") == -1) { // 返回backurl所对应的方法

			return "forward:/" + backurl;
		}

		// 返回scenicspotdetails

		return "scenicspotdetails";

	}

	@ResponseBody
	// 定义Scenicspot详情方法

	@RequestMapping(value = "scenicspotdetailsjson")

	public Map scenicspotdetailsjson(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			int id, String backurl) {

		// 输入日志信息，表名当前执行方法为ScenicspotController.scenicspotdetailsjson

		logger.debug("ScenicspotController.scenicspotdetailsjson ......");

		Map result = new HashMap();

		result = scenicspotservice.scenicspotdetailsjson(id);

		// 将结果以json返回

		return result;

	}

}
