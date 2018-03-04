package com.open.source.inc.auction.entities;

import java.util.List;

public class Book extends FusionEntity{
	
	protected Book(){		
	}
	
	public Book(List<Object> row) {
		super(row);
		setTitle  ((String) row.get(2));
		setAuthor ((String) row.get(3));
		setEdition((String) row.get(4));
		
	}
	
	private String title;
	private String author;
	private String edition;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
}
