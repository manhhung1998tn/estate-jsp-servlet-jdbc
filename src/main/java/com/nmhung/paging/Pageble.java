package com.nmhung.paging;

public interface Pageble {
	Integer getPage();
	Integer getOfSet();
	Integer getLimit();
	Sorter getSorter();
	void setSorter(Sorter sorter);
	
}
