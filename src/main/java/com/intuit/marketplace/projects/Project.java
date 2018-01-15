package com.intuit.marketplace.projects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.intuit.marketplace.bids.Bid;

@Document(collection="projects")
public class Project {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private String status;
	
	private float budget;
	
	private String requirements;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date lastDayForBid;
	
	private List<Bid> bids;
	
	private  double leastBidAmount;
	
	public void setLeastBidAmount(double leastBidAmount) {
		this.leastBidAmount = leastBidAmount;
	}

	public double getLeastBidAmount() {
		return leastBidAmount;
	}


	public Project()
	{
		this.bids=new ArrayList<Bid>();
	}

	public Project(String id, String name, String description, String status, float budget, String requirements,
			Date lastDayForBid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.budget = budget;
		this.requirements = requirements;
		this.lastDayForBid = lastDayForBid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Date getLastDayForBid() {
		return lastDayForBid;
	}

	public void setLastDayForBid(Date lastDayForBid) {
		this.lastDayForBid = lastDayForBid;
	}

	public List<Bid> getBids() {
		return bids;
	}
	
}
