package com.tutorial.tracker.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_Expense")
public class Expense {
	@Id
	@SequenceGenerator(name = "expense_sequence",sequenceName = "expense_sequence",allocationSize = 1,initialValue = 4000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "expense_sequence")
	private Long expenseId;
	
	
	private String title;

    private String description;
	
    private Double price;

    private LocalDate date;
    private LocalTime time;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_userId")
    private User user;
    
    
    
   
	

}
