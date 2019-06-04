package com.nmhung.paging;

public class PageRequest implements Pageble {

	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	


	public PageRequest(Integer page, Integer maxPageItem,Sorter sorter) {
		super();
		this.sorter = sorter;
		this.page = page;
		this.maxPageItem = maxPageItem;
	}
	
	

	public Integer getMaxPageItem() {
		return maxPageItem;
	}



	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}



	public Sorter getSorter() {
		return sorter;
	}



	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}



	public void setPage(Integer page) {
		this.page = page;
	}



	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	public Integer getOfSet() {
		// TODO Auto-generated method stub
		if(page != null && maxPageItem != null) {
			return (page-1)*maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return maxPageItem;
	}
	
}
