package com.tutorial.tracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDto {
	
	@NotNull
	private String title;
	
	@NotNull
    private String description;
    @NotNull
    private Double price;

    private LocalDate date;
    private LocalTime time;
    
    

}
