package com.tutorial.tracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpInput {
	
	@NotEmpty
	private String userName;
	@Email
	private String userEmail;
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String userPassword;

}
