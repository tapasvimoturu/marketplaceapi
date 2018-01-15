package com.intuit.marketplace.bids;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRespository extends MongoRepository<Bid, String> {

	
	public List<Bid> findByProjectId(String id);
}
