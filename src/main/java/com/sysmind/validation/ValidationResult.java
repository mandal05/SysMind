package com.sysmind.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ValidationResult { 
	public ValidationResult() {
		errors = new ArrayList<String>();
	}
	private String errorType = "None";
    private List<String> errors;    
    private boolean isValid = true;
    public void addError(String error, String errorType)
    {
    	this.errorType = errorType;
    	this.isValid = false;
    	this.errors.add(error);
    }
}
