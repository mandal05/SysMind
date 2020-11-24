package com.sysmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@Entity
@Table(name="wordEntity")
public class WordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "word", nullable = false)
	private String word;

}
