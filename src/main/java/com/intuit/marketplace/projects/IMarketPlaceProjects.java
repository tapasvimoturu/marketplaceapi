package com.intuit.marketplace.projects;

import java.util.List;

import com.intuit.marketplace.exceptions.APIEntityNotFoundException;
import com.intuit.marketplace.exceptions.MandatoryFieldNotFoundException;

public interface IMarketPlaceProjects {
	
	 List<Project> getAllProjects();
	 
	 List<Project> getAllOpenProjects();
	 
	 Project  createProject(Project project) throws MandatoryFieldNotFoundException;
	   
	 Project getProjectById(String id) throws APIEntityNotFoundException;
	 
	 Project updateProject(Project project);
	 

}
