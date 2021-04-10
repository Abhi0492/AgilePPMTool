package io.agileintelligence.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	
	@Autowired
	private ProjectService projectService;
	
	 
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		if(result.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<String, String>();
			
			for(FieldError flderr : result.getFieldErrors()) {
				errorMap.put(flderr.getField(), flderr.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
			
			//return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		
		Project project1 = projectService.saveorUpdateProject(project);
		return new  ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	
}
