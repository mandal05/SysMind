package com.sysmind.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Request {
	public String word;
	public List<String> wordList;
}
