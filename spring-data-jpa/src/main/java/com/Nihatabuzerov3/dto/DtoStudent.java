package com.Nihatabuzerov3.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudent {
	
	private Integer id;

	private String firstName;
	
	private String lastName;
	
	private List<DtoCourse> courses = new ArrayList<>();
}
