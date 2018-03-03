package com.open.source.inc.auction.daos;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.open.source.inc.auction.entities.FusionEntity;

public abstract class FusionDAO<T extends FusionEntity> {

    @Autowired ServletContext servletCtx;
    static GoogleCredential credential;
    static final GenericUrl FUSION_TABLE_URL = new GenericUrl("https://www.googleapis.com/fusiontables/v2/query");
    static HttpRequestFactory requestFactory;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    protected static Fusiontables fusiontables;
    
    @PostConstruct
    private void init() throws IOException, GeneralSecurityException {

    	HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    	requestFactory = httpTransport.createRequestFactory();
		credential = GoogleCredential.fromStream(servletCtx.getResourceAsStream("/WEB-INF/keys/kean-college-router-0ce6612db7e3.json"))
			    .createScoped(Collections.singleton("https://www.googleapis.com/auth/fusiontables"));
		fusiontables = new Fusiontables.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName("KeanBooks").build();
    	
    }    
        
    public String getListJSON() throws IOException, GeneralSecurityException {

		return doJSON(getSelectSQL()); 
	
    }
    
	public String getListJSON(String name, String value, String orderByName) throws IOException, GeneralSecurityException {

		return doJSON(getSelectSQL() + " where " + name + " = \"" + value + "\" order by " + orderByName); 
	
	}
    public String createJSON(T t) throws IOException, GeneralSecurityException {

		return doJSON(getInsertSQL(t)); 
	}
	public String updateJSON(T t) throws IOException, GeneralSecurityException {

		return doJSON(getUpdateSQL(t)); 
	}
	public String readJSON(T t) throws IOException, GeneralSecurityException {

		return doJSON(getSelectSQL(t.getRowId())); 
	}

	public String deleteJSON(T t) throws IOException, GeneralSecurityException {

		return doJSON(getDeleteSQL(t.getRowId())); 
	}
    
    protected abstract List<T>	getListByItemId	(String itemId) throws IOException;
    protected abstract List<T>	getListbyUser	(String userId) throws IOException;
    protected abstract T 		getBestDeal		(String itemId) throws IOException;
    protected abstract String getTableName();
    protected abstract String getColumnNames();
	protected abstract String getInsertSQL(T t) ;
	protected abstract String getUpdateSQL(T t) ;
	
	protected String getSelectSQL(){
		
		return "select rowid, " + getColumnNames() +  " from " + getTableName();
		
	}
	
	protected String getSelectSQL(String name, String value, String orderByName) {

		return getSelectSQL() + " where " + name + " = \"" + value + "\" order by " + orderByName; 
	
	}
		
	protected String getSelectSQL(Integer integer){
		
		return getSelectSQL() + " where rowid = " + integer ;
		
	}
			
	private String getDeleteSQL(Integer integer){
		
		return "delete from " + getTableName() + " where rowid = " + integer ;
		
	}
		
    private String doJSON(String SQL) throws IOException, GeneralSecurityException{
    	
	    byte[] buf = new byte[16384];
	    StringBuffer buffer = new StringBuffer(16384);
	    InputStream gzis = doSQL(SQL);
	    while ((gzis.read(buf)) > 0) {
	    	 buffer.append(new String(buf).trim());
	    }
	    gzis.close();
	    buf = null;
    	return buffer.toString();
    	
    }
	              
    private InputStream doSQL(String SQL) throws IOException{
    	
		Map<Object, Object> params = new HashMap<>(); 
      	params.put("sql", SQL);
      	params.put("hdrs", false);
      	params.put("typed", true);
      	//params.put("alt", "media");

	    HttpRequest request = requestFactory.buildPostRequest(FUSION_TABLE_URL, new UrlEncodedContent(params)); 
	    credential.initialize(request);
	    return request.execute().getContent();

    }
}
/*	private String stripBrackets (String location){

StringBuilder sb = new StringBuilder(location);
sb.deleteCharAt(sb.lastIndexOf("("));
sb.deleteCharAt(sb.lastIndexOf(")"));
return sb.toString();

}*/