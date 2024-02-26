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

import org.mypro.dao.HistoryMapper;

import org.mypro.entity.History;

import org.mypro.entity.HistoryExample;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

public interface HistoryServiceImp {

	// 定义方法addhistory接口,响应控制层添加访问历史请求

	public void addhistory(HttpServletRequest request, HttpSession session);

	// 定义addhistoryact接口

	public void addhistoryact(HttpServletRequest request, HttpSession session, History history) throws IOException; // 定义addhistoryactjson接口

	public void addhistoryactjson(History history) throws IOException;

	public void userhistorymanage(HttpServletRequest request, HttpSession session);

	public void historymanage(HttpServletRequest request, HttpSession session);

	// 定义 historyview方法

	public void historyview(HttpServletRequest request, HttpSession session);

	// 定义 updatehistory方法

	public void updatehistory(HttpServletRequest request, HttpSession session, int id);

	// 定义updatehistoryact处理访问历史修改

	public void updatehistoryact(HttpServletRequest request, History history, HttpSession session) throws IOException; // 定义updatehistoryactjson处理访问历史修改

	public void updatehistoryactjson(History history) throws IOException;

	// 定义deletehistory,处理删除访问历史

	public void deletehistory(HttpServletRequest request, HttpSession session, int id);

	// 定义searchhistory方法，处理搜索操作

	public void searchhistory(HttpServletRequest request, HttpSession session, String search);

	// 定义searchhistoryjson方法，处理搜索操作

	public Map searchhistoryjson(String search);

	// 定义HistorypinglunMapper

	public void historydetails(HttpServletRequest request, HttpSession session, int id);

	// 定义History详情方法

	public Map historydetailsjson(int id);

}
