package com.intuit.marketplace.bids;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intuit.marketplace.exceptions.APIEntityNotFoundException;
import com.intuit.marketplace.projects.Project;
import com.intuit.marketplace.projects.ProjectRepository;
import com.intuit.marketplace.util.MarketPlaceUtil;



@Service
public class MarketPlaceBidSrvcImpl {

	@Autowired
	private BidRespository bidRespository;

	@Autowired
	private ProjectRepository projectRepo;

	public ResponseEntity<String> createBidForProject(String projectId, Bid bid) {

		Project project = projectRepo.findOne(projectId);
		if (project == null) {

			return new ResponseEntity<String>("Cannot find the project/Invalid Project Id", HttpStatus.PRECONDITION_REQUIRED);
		}
		Date bidDate = bid.getCreatedDate();
		Date projectDate = project.getLastDayForBid();
		boolean isBidDateOver = MarketPlaceUtil.isBidDateOver(bidDate, projectDate);
		if (isBidDateOver) {
			return new ResponseEntity<String>("Bid Date is Over", HttpStatus.PRECONDITION_REQUIRED);
		}
		bid.setId("BID_" + String.valueOf(System.currentTimeMillis()));
		bid.setProjectId(projectId);
		Bid newBid = bidRespository.save(bid);
		project.getBids().add(newBid);
		projectRepo.save(project);

		return new ResponseEntity<String>("Bid is created for the Project", HttpStatus.OK);
	}
    
	
	public List<Bid> getAllBidsForProject(String projectId) throws  APIEntityNotFoundException {
		List<Bid> allBids = bidRespository.findAll();
		Project project = projectRepo.findOne(projectId);
		if(project==null)
		{
			throw new APIEntityNotFoundException("Cannot delete Bid,Project Not found");
		}
		//return allBids;
		return allBids.stream().filter(bid -> bid.getProjectId().equalsIgnoreCase(projectId))
				.collect(Collectors.toList());
	}
	
	public ResponseEntity<String> deleteBid(String projectId,String bidId) throws APIEntityNotFoundException
	{
		Project project = projectRepo.findOne(projectId);
		if(project==null)
		{
			throw new APIEntityNotFoundException("Cannot delete Bid,Project Not found");
		}
		bidRespository.delete(bidId);
		project.getBids().removeIf(bid->bid.getId().equalsIgnoreCase(bidId));
		projectRepo.save(project);
		
		return new ResponseEntity<String>("Bid Deleted",HttpStatus.OK);
	}
	
	public ResponseEntity<String> updateBid(String projectId,Bid bid) throws APIEntityNotFoundException
	{
		Project project = projectRepo.findOne(projectId);
		if(project==null)
		{
			throw new APIEntityNotFoundException("Cannot delete Bid,Project Not found");
		}
		bidRespository.save(bid);
		projectRepo.save(project);
		return new ResponseEntity<String>("Bid Updated",HttpStatus.OK);
	}
	
	

}
