package org.mypro.service;

import java.io.File;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.HashMap;

import java.util.Map;

import java.util.ArrayList;

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

import org.mypro.dao.ScenicspotMapper;

import org.mypro.entity.Scenicspot;

import org.mypro.entity.ScenicspotExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

public interface ScenicspotServiceImp {

	// 定义方法addscenicspot接口,响应控制层添加景点请求

	public void addscenicspot(HttpServletRequest request, HttpSession session);

	// 定义addscenicspotact接口

	public void addscenicspotact(HttpServletRequest request, HttpSession session, Scenicspot scenicspot)
			throws IOException; // 定义addscenicspotactjson接口

	public void addscenicspotactjson(Scenicspot scenicspot) throws IOException;

	// 定义scenicspotmanage方法响应页面请求

	public void scenicspotmanage(HttpServletRequest request, HttpSession session);

	// 定义 scenicspotview方法

	public void scenicspotview(HttpServletRequest request, HttpSession session);

	// 定义 updatescenicspot方法

	public void updatescenicspot(HttpServletRequest request, HttpSession session, int id);

	// 定义updatescenicspotact处理景点修改

	public void updatescenicspotact(HttpServletRequest request, Scenicspot scenicspot, HttpSession session)
			throws IOException; // 定义updatescenicspotactjson处理景点修改

	public void updatescenicspotactjson(Scenicspot scenicspot) throws IOException;

	// 定义deletescenicspot,处理删除景点

	public void deletescenicspot(HttpServletRequest request, HttpSession session, int id);

	// 定义searchscenicspot方法，处理搜索操作

	public void searchscenicspot(HttpServletRequest request, HttpSession session, String search);

	// 定义searchscenicspotjson方法，处理搜索操作

	public Map searchscenicspotjson(String search);

	// 定义ScenicspotpinglunMapper

	public void scenicspotdetails(HttpServletRequest request, HttpSession session, int id);

	// 定义Scenicspot详情方法

	public Map scenicspotdetailsjson(int id);

}
