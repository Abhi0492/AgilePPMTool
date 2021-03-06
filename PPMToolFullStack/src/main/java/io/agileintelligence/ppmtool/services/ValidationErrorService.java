package io.agileintelligence.ppmtool.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorService {

	
	public ResponseEntity<?> mapBasedValidationService(BindingResult result) {
		
		System.out.println("________Into ValidationErrorService________");
		if(result.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<String, String>();
			
			for(FieldError flderr : result.getFieldErrors()) {
				errorMap.put(flderr.getField(), flderr.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
			
			//return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}
}
