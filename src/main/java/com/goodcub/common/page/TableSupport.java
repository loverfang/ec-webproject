package com.goodcub.common.page;

/**
 * 表格数据处理
 * 
 * @author yanjie
 */
public class TableSupport {
	//默认当前页
	private static final int DEFAULE_PAGE_INDEX = 1;
	
	//默认每页大小
	private static final int DEFAULE_PAGE_SIZE = 10;
	
	/**
	 * 封装分页对象
	 */
	public static PageDomain buildPageDomain() {
		PageDomain pageDomain = new PageDomain();
		//pageDomain.setPageIndex(ServletUtils.getParameterToInt(Constants.PAGE_INDEX, DEFAULE_PAGE_INDEX));
		//pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE, DEFAULE_PAGE_SIZE));
		//pageDomain.setSortField(ServletUtils.getParameter(Constants.SORT_FIELD));
		//pageDomain.setSortOrder(ServletUtils.getParameter(Constants.SORT_ORDER));
		return pageDomain;
	}
}
