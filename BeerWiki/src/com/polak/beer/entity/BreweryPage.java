package com.polak.beer.entity;

import java.util.List;

public class BreweryPage {

	private long page;
	private long pages;
	private long total;
	private List<Brewery> breweries;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Brewery> getBreweries() {
		return breweries;
	}

	public void setBreweries(List<Brewery> breweries) {
		this.breweries = breweries;
	}

}
