package com.open.source.inc.auction.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.open.source.inc.auction.entities.Book;

@Repository
public class BookDAO extends FusionDAO<Book>{

	@Override
	public Book getBestDeal(String itemId) throws IOException {
		
		List<Book> list = getList("rowId",itemId,"title");
		return list.isEmpty()? null : list.get(0);
	
	}

	@Override
	public List<Book> getListByItemId(String itemId) throws IOException {
    	
		return getList("id",itemId,"price");
		
	}

	@Override
	public List<Book> getListbyUser(String userId) throws IOException {
		
		return getList("email",userId,"id");

	}

	private List<Book> getList(String name, String value, String sortBy ) throws IOException {
		
		ArrayList <Book> 	list = new ArrayList<Book>();
		List<List<Object>> 	dataset = fusiontables.query().sql(getSelectSQL(name, value, sortBy)).execute().getRows();
		if (dataset != null){
			for ( List<Object> row : dataset){			
				list.add(new Book(row));
			};
		}
        return list; 
		
	}
	
	@Override
	protected String getTableName() {
		return "1C87FBQERJRx27zz9l6c9jp9py9mGNFaeOyEC9u9h";
	}

	@Override
	protected String getColumnNames() {
		return "id, title, author, edition";
	}

	@Override
	protected String getInsertSQL(Book book) {

	    	return "insert into " + getTableName() + " (" + getColumnNames() + ") values "
			+ "("

			+ book.getId() // ISBN
			+ ","
			
			+ "'"
			+ book.getTitle()
			+ "'"
			+ ","
			
			+ "'"
			+ book.getAuthor()
			+ "'"
			+ ","
			
			+ "'"
			+ book.getEdition()
			+ "'"
			
			+ ")";
		}

	@Override
	protected String getUpdateSQL(Book t) {
		// TODO Auto-generated method stub
		return null;
	}

}
