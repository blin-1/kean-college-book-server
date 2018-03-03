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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.open.source.inc.auction.entities.Bid;
import com.open.source.inc.auction.services.AuctionService;

@RestController
@CrossOrigin
@RequestMapping(path="/bids")
public class BidController {
	
	@Autowired AuctionService auctionService;
		
	@GetMapping("/byId")
	public void getById (@RequestParam(required = true) String id, HttpServletResponse response) throws IOException, GeneralSecurityException{
				
		response.getWriter().println (auctionService.getBids(id)); 

	}
	
	@PostMapping("/create")
	public void create (@RequestBody Bid bid, HttpServletResponse response) throws IOException, GeneralSecurityException {

		response.getWriter().println(auctionService.registerBid(bid));
		
	}
}