package com.sysmind.model;

import com.sysmind.validation.ValidationResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Response {
	public Response() {
		validationResult = new ValidationResult();
		indexes = new ArrayList<Integer>();
	}
	public ValidationResult validationResult;
	private List<Integer> indexes;
}
