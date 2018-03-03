package com.open.source.inc.auction.daos;

import java.util.Date;

import com.open.source.inc.auction.entities.Bet;

public abstract class BetDAO<T extends Bet> extends FusionDAO<T>{
	
	@Override
	protected String getColumnNames() {
		return "id, price, date, email, phone, notes";
	}
	
	@Override
	protected String getInsertSQL(Bet t) {

    	return "insert into " + getTableName() + " (" + getColumnNames() + ") values "
		+ "("

		+ t.getId() // ISBN
		+ ","
		
		+ t.getPrice()
		+ ","
		
		+ "'"
		+ new Date()
		+ "'"
		+ ","
		
		+ "'"
		+ t.getEmail()
		+ "'"
		+ ","
		
		+ "'"
		+ t.getPhone()
		+ "'"
		+ ","

		+ "'"
		+ t.getNotes()
		+ "'"
		
		+ ")";
	}

	@Override
	protected String getUpdateSQL(Bet t) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
