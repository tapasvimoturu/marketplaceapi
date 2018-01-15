package com.intuit.marketplace.projects;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intuit.marketplace.exceptions.APIEntityNotFoundException;
import com.intuit.marketplace.exceptions.MandatoryFieldNotFoundException;

@RestController
public class ProjectController {

	@Autowired
	private MarketPlaceProjectsSrvcImpl marketPlaceService;
	

	@RequestMapping(method = RequestMethod.GET, value = "/marketplace/api/projects")
	public List<Project> getAllProjects(
			@RequestParam(value = "status", required = false, defaultValue = "OPEN") String status) {

		return marketPlaceService.getAllProjects().stream()
				.filter(project -> project.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());

	}

	@RequestMapping(method = RequestMethod.GET, value = "/marketplace/api/projects/{id}")
	public Project getProjectById(@PathVariable String id) throws APIEntityNotFoundException{

		return marketPlaceService.getProjectById(id);

	}

	@RequestMapping(method=RequestMethod.POST,value="/marketplace/api/projects")
	public ResponseEntity<String> createProject(@RequestBody Project newProject, UriComponentsBuilder uriBuilder) throws MandatoryFieldNotFoundException {
		newProject.setId("PROJ_" + String.valueOf(System.currentTimeMillis()));

		marketPlaceService.createProject(newProject);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriBuilder.path("marketplace/api/projects/{id}").buildAndExpand(newProject.getId()).toUri());
		return new ResponseEntity<String>("Project Created Succesfully", headers, HttpStatus.CREATED);
	}

	@PutMapping("/marketplace/api/projects")
	public ResponseEntity<String> updateProject(@RequestBody Project project, UriComponentsBuilder uriBuilder) {
		Project updatedProject = marketPlaceService.updateProject(project);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriBuilder.path("marketplace/api/projects/{id}").buildAndExpand(updatedProject.getId()).toUri());
		return new ResponseEntity<String>("Project Updated Succesfully", headers, HttpStatus.ACCEPTED);
	}

}
