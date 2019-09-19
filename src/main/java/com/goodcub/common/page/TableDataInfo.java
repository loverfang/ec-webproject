package com.goodcub.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author liuyong
 */
public class TableDataInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 总记录数 */
	private long total;
	/** 列表数据 */
	private List<?> data;

	/**
	 * 表格数据对象
	 */
	public TableDataInfo() {
	}

	/**
	 * 分页
	 * 
	 * @param data  列表数据
	 * @param total 总记录数
	 */
	public TableDataInfo(List<?> data, int total) {
		this.data = data;
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
