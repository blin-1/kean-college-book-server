package com.open.source.inc.auction.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.open.source.inc.auction.entities.Offer;

@Repository
public class OfferDAO extends BetDAO<Offer>{

	@Override
	public Offer getBestDeal(String itemId) throws IOException{
			
		List<Offer> list = getListByItemId(itemId);
		return list.isEmpty()? null : list.get(0);
		
	}	
	
	@Override
	public List<Offer> getListByItemId(String itemId) throws IOException {
    	
		return getList("id",itemId,"price");
		
	}

	@Override
	public List<Offer> getListbyUser(String userId) throws IOException {
		
		return getList("email",userId,"id");

	}
		
	private List<Offer> getList(String name, String value, String sortBy ) throws IOException {
		
		ArrayList <Offer> list = new ArrayList<Offer>();
		List<List<Object>> dataset = fusiontables.query().sql(getSelectSQL(name, value, sortBy)).execute().getRows();
		if (dataset != null){
			for ( List<Object> row : dataset){			
				list.add(new Offer(row));
			};
		}
        return list; 
        
	} 
	
	@Override
	protected String getTableName() {
		return "1dAcV0eeqoXaYLu-vJeBZByj1Qr0R-kTuKkWQFWqu";
	}
}
