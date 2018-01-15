package com.intuit.marketplace.bids;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intuit.marketplace.exceptions.APIEntityNotFoundException;

@RestController
public class BidController {

	@Autowired
	private MarketPlaceBidSrvcImpl marketPlaceBidService;

	@RequestMapping(method = RequestMethod.POST, value = "/marketplace/api/projects/{projectId}/bids")
	public ResponseEntity<String> addBidToProject(@PathVariable String projectId, @RequestBody Bid bid,UriComponentsBuilder uriBuilder) {
		ResponseEntity<String> responseFromService=marketPlaceBidService.createBidForProject(projectId, bid);	
		return responseFromService;
  
	}

	@RequestMapping(method = RequestMethod.GET, value = "/marketplace/api/projects/{projectId}/bids")
	public List<Bid> getAllBids(@PathVariable String projectId)  throws APIEntityNotFoundException{
		List<Bid> bids = new ArrayList<Bid>();
		bids= marketPlaceBidService.getAllBidsForProject(projectId);
		return bids;
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/marketplace/api/projects/{projectId}/bids/{bidId}")
	public ResponseEntity<String> deleteBid(@PathVariable String projectId,@PathVariable String bidId) throws APIEntityNotFoundException
	{
		ResponseEntity<String> responseFromSrvc = marketPlaceBidService.deleteBid(projectId, bidId);
		return responseFromSrvc;
	   
	}
	@PutMapping("/marketplace/api/projects/{projectId}/bids")
	public ResponseEntity<String> updateProject(@PathVariable String projectId, @RequestBody Bid bid, UriComponentsBuilder uriBuilder) throws APIEntityNotFoundException  {
		ResponseEntity<String> responseFromSrvc = marketPlaceBidService.updateBid(projectId,bid);
		return responseFromSrvc;
	}

}
