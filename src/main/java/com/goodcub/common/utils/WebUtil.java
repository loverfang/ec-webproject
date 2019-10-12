package com.goodcub.common.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	/**
	 * 获取登录员工
	 */
	/*
	 * public static Employee getEmployee(HttpServletRequest request){
	 * 
	 * return (Employee) request.getSession().getAttribute("employee");
	 *  }
	 */
	/***************************************************************************
	 * 获取URI的路径,如路径为http://www.babasport.com/action/post.htm?method=add,
	 * 得到的值为"/action/post.htm"
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest request) {
		System.out.println("得到的" + request.getRequestURI().substring(11));
		return request.getRequestURI().substring(10);
	}

	/**
	 * 获取完整请求路径(含内容路径及请求参数)
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestURIWithParam(HttpServletRequest request) {
		return getRequestURI(request)
				+ (request.getQueryString() == null ? "" : "?"
						+ request.getQueryString());
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie的名称
	 * @param value
	 *            cookie的值
	 * @param maxAge
	 *            cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param request
	 * @param name
	 *            cookie的名称
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 删除cookie
	 */
	public static void clearCookieByName(HttpServletRequest request,
			HttpServletResponse response, String path, String cookieName,
			String cookieValue) {

		Cookie[] cookies = request.getCookies();

		try {

			for (int i = 0; i < cookies.length; i++) {

				Cookie cookie = new Cookie(cookieName, cookieValue);

				cookie.setMaxAge(0);

				cookie.setPath(path);// 根据你创建cookie的路径进行填写

				response.addCookie(cookie);

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * 获得指定长度的数字字符串
	 */
	public static String getNumStrByLength(int length) {
		String s = "";
		while (s.length() < length){
			s += (int) (Math.random() * 10);
		}
		return s;
	}
	

	protected static Map<String, Cookie> readCookieMap(
			HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
		return cookieMap;
	}

	/**
	 * 去除html代码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String HtmltoText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		java.util.regex.Matcher m_script;
		Pattern p_style;
		java.util.regex.Matcher m_style;
		Pattern p_html;
		java.util.regex.Matcher m_html;
		Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * 组装order by语句 将<字段,排序方式>的Map对象组成HQL语句
	 * 
	 * @param orderby
	 * @return
	 */
	public static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyHQL = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyHQL.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyHQL.append("o.").append(key).append(" ").append(
						orderby.get(key)).append(",");
			}
			orderbyHQL.deleteCharAt(orderbyHQL.length() - 1);
		}
		return orderbyHQL.toString();
	}

	public static void main(String[] args) {
		System.out.println(WebUtil.getNumStrByLength(5));
	}
}
