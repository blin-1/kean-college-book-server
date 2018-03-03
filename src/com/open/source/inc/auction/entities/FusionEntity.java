package com.open.source.inc.auction.entities;

import java.util.List;

public abstract class FusionEntity {
	
	protected FusionEntity(){
		
	}
	
	protected FusionEntity(List<Object> row){
		
		setRowId(Integer.parseInt((String) row.get(0)));
		setId((String) row.get(1));
		
	};
			
	private Integer rowId;
	private String  id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	
}
