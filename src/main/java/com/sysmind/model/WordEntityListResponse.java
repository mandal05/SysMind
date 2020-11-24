package com.sysmind.model;

import com.sysmind.validation.ValidationResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class WordEntityListResponse {
	public WordEntityListResponse() {
		validationResult = new ValidationResult();
		result = new ArrayList<WordEntity>();
	}
	public ValidationResult validationResult;
	private List<WordEntity> result;


}
