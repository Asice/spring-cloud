package com.qurich.common.model;

public class PageMeta {

	private int page;  //当前页
	private int pages; //总页数
	private int perpage;//一页多少个
	private int total;//总个数
	private String sort;
	private String field;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		if(perpage==0)
			return 0;
		if(total%perpage==0)
			return total/perpage;
		else
			return total/perpage+1;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}
