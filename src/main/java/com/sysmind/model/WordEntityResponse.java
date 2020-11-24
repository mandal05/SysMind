package com.sysmind.model;

import com.sysmind.validation.ValidationResult;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class WordEntityResponse {
	public WordEntityResponse() {
		validationResult = new ValidationResult();
	}
	public ValidationResult validationResult;
	private boolean result;

}
