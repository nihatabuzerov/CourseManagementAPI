package com.Nihatabuzerov3.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

	@NotEmpty(message = "Firstname boş olmamalidir!")

	private String firstName;
	
	@Size(min = 3 , max = 30)
	private String lastName;
	
	private Date birthOfDate;

	@Email(message = "Email formatında bir adres girin!")
	private String email;

}
