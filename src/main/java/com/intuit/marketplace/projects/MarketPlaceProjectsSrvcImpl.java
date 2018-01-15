package com.intuit.marketplace.projects;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.marketplace.bids.Bid;
import com.intuit.marketplace.exceptions.APIEntityNotFoundException;
import com.intuit.marketplace.exceptions.MandatoryFieldNotFoundException;

@Service
public class MarketPlaceProjectsSrvcImpl implements IMarketPlaceProjects {

	@Autowired
	private ProjectRepository projectRepo;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	
	@Override
	public Project createProject(Project project) throws MandatoryFieldNotFoundException {
		if (project != null && project.getName() == null || "".equals(project.getName())) {
			throw new MandatoryFieldNotFoundException("Project Name is Mandatory");
		}
		return projectRepo.save(project);
	}

	@Override
	public List<Project> getAllOpenProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProjectById(String id) throws APIEntityNotFoundException {

		Project project = projectRepo.findOne(id);

		if (project == null) {
			throw new APIEntityNotFoundException("Project Not found with ID " + id);
		}

		if (project != null) {
			List<Bid> bids = project.getBids();
			Bid mindBid = bids.stream().min(Comparator.comparing(Bid::getAmount)).orElse(null);
			project.setLeastBidAmount(mindBid != null ? mindBid.getAmount() : 0);

		}
		return project;
	}

	@Override
	public Project updateProject(Project project) {
		return projectRepo.save(project);
	}

}
