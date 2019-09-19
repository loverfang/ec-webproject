package com.goodcub.common.page;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页数据
 * 
 * @author liuyong
 */
public class PageDomain {
	/** 当前记录起始索引 */
	private Integer pageIndex;

	/** 每页显示记录数 */
	private Integer pageSize;

	/** 排序字段 */
	private String sortField;

	/** 排序的方向 "desc" 或者 "asc". */
	private String sortOrder;

	public String getOrderBy() {
		if (StringUtils.isEmpty(sortField)) {
			return "";
		}
		//return StringUtils.uner .toUnderScoreCase(sortField) + " " + sortOrder;
		return null;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
