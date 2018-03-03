package com.open.source.inc.auction.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.open.source.inc.auction.entities.Bid;

@Repository
public class BidDAO extends BetDAO<Bid>{
	
	@Override
	public Bid getBestDeal(String itemId) throws IOException {

		List<Bid> list = getListByItemId(itemId);
		return list.isEmpty()? null : list.get(list.size() - 1);
		
	}
	
	@Override
	public List<Bid> getListByItemId(String itemId) throws IOException {
    	
		return getList("id",itemId,"price");
		
	}

	@Override
	public List<Bid> getListbyUser(String userId) throws IOException {
		
		return getList("email",userId,"id");

	}

	private List<Bid> getList(String name, String value, String sortBy ) throws IOException {
		
		ArrayList <Bid> list = new ArrayList<Bid>();
		List<List<Object>> dataset = fusiontables.query().sql(getSelectSQL(name, value, sortBy)).execute().getRows();
		if (dataset != null){
			for ( List<Object> row : dataset){			
				list.add(new Bid(row));
			};
		}
        return list; 
		
	}
	
	@Override
	protected String getTableName() {
		return "1vpmR8Jj_NbOHfVndJD-T72DbsfTA-LDXYGCNYgOC";
	}
	
}
