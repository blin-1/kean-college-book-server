package com.open.source.inc.auction.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class Bet extends FusionEntity{
	
	protected Bet(){
		
	}
	
	protected Bet(List<Object> row) {
		super(row);
		BigDecimal price = (BigDecimal) row.get(2);
		setPrice(Double.parseDouble(price.toString()));
		//setDate(date);//3
		setEmail((String) row.get(4));
		setPhone((String) row.get(5));
		setNotes((String) row.get(6));
		
	}
	private Double  price;	
	private Date    date;
	private String  email;
	private String  phone;
	private String	notes;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
