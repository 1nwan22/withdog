package com.withdog.common;

import org.springframework.stereotype.Component;

@Component
public class Paging {

	private final int bundlePage = 5;
	
	public int getMinBundlePage(int currentPage) {
		return ((currentPage) / bundlePage) * bundlePage + 1;
	}
	
	public int getMaxBundlePage(int currentPage, int totalPages) {
	    int maxBundlePage = (currentPage / bundlePage) * bundlePage + bundlePage;
	    return maxBundlePage > totalPages ? totalPages : maxBundlePage;
	}
}
