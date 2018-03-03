package com.open.source.inc.auction.services;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.source.inc.auction.daos.BidDAO;
import com.open.source.inc.auction.daos.BookDAO;
import com.open.source.inc.auction.daos.OfferDAO;
import com.open.source.inc.auction.entities.Bid;
import com.open.source.inc.auction.entities.Book;
import com.open.source.inc.auction.entities.Offer;

@Service
public class AuctionService {
	
	@Autowired BidDAO	bidDAO;
	@Autowired OfferDAO	offerDAO;
	@Autowired BookDAO	bookDAO;
	@Autowired MailService mailSvc;

	public String getBids(String id) throws IOException, GeneralSecurityException {

		return bidDAO.getListJSON("id", id, "price");

	}
	
	public String getOffers(String id) throws IOException, GeneralSecurityException {

		return offerDAO.getListJSON("id", id, "price");

	}

	public String registerBid(Bid bid) throws IOException, GeneralSecurityException {
		
		Offer deal = offerDAO.getBestDeal(bid.getId());
		if (deal != null && deal.getPrice() <= bid.getPrice()){
			return processTrade(deal,bid,true);
		}else{
			return bidDAO.createJSON(bid);
		}
		
	}
	
	public String registerOffer(Offer offer) throws IOException, GeneralSecurityException {
				
		Bid deal = bidDAO.getBestDeal(offer.getId());
		if (deal != null && deal.getPrice() >= offer.getPrice()){
			return processTrade(offer,deal,false);
		}else{
			return offerDAO.createJSON(offer);
		}
		
	}
	
	private String processTrade(Offer offer, Bid bid, boolean dealInitiatedOnBidSide) throws IOException, GeneralSecurityException {
		
		String response = "{}";			
		Double price = dealInitiatedOnBidSide? offer.getPrice() : bid.getPrice();
		String [] emails = {offer.getEmail(),bid.getEmail()};
		Book book = bookDAO.getBestDeal(offer.getId());
		
		if (book == null) throw new GeneralSecurityException("Book with id = " + offer.getId() + " was not found ");
		mailSvc.sendMail(emails, "Book Deal on " + book.getTitle() + " by " + book.getAuthor(), "Please get in touch via email\nThe agreed price is " + price.toString());
		if (dealInitiatedOnBidSide){
			offerDAO.deleteJSON(offer);
		}else{
			bidDAO.deleteJSON(bid);
		}
		
		return response;
		
	}
	
	
	public String getItems() throws IOException, GeneralSecurityException {

		return bookDAO.getListJSON();

	}

	public String addItem(Book book) throws IOException, GeneralSecurityException {

		return bookDAO.createJSON(book);
	
	}
	
}
