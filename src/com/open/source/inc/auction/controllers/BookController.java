package com.open.source.inc.auction.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.source.inc.auction.entities.Book;
import com.open.source.inc.auction.services.AuctionService;

@RestController
@CrossOrigin
@RequestMapping(path="/books")
public class BookController{

	@Autowired AuctionService auctionService;
	
	@GetMapping("/")
	public void getAll (HttpServletResponse response) throws IOException, GeneralSecurityException{
				    
	    response.getWriter().println (auctionService.getItems());
   
	}
	
	@PostMapping("/create")
	public void create (@RequestBody Book book,HttpServletResponse response) throws IOException, GeneralSecurityException {
		
		response.getWriter().println(auctionService.addItem(book));
		
	}
    
}