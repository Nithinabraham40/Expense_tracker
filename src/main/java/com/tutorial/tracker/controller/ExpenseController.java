package com.tutorial.tracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.tracker.Repository.UserRepository;
import com.tutorial.tracker.dto.ExpenseDto;
import com.tutorial.tracker.service.ExpenseService;



@RestController
@RequestMapping("/expense")

public class ExpenseController {
	
	
	@Autowired
	private ExpenseService expenseServise;
	
	
	
	

	
	
	//adding the expenses by userid
	
	@PostMapping("addExpense/userid/{id}")
		
	public ResponseEntity<String>addExpenses(@PathVariable("id") Long userId,@RequestBody ExpenseDto expenseDto){
		
		return expenseServise.addExpenses(userId,expenseDto);
		
	}
	
	// to view user expense by userid
	
	@GetMapping("view/expense/userid/{id}")
	
	public List<ExpenseDto>allYourexpens(@PathVariable("id")Long id){
	  
		return expenseServise.getallExpense(id);
		
	}
	
	// to delete expense with expense date and userid
	
	@DeleteMapping("delete/date/{date}/userid/{id}")
	
	public ResponseEntity<String>deleteResponse(@PathVariable("date")String date,@PathVariable("id")Long id){
		
		
		return expenseServise.deleteResponse(date,id);
	}
	
	//to get all expense by date and user id
	
	@GetMapping("get/date/{date}/userid/{id}")
	
	public List<ExpenseDto>getAllYourExpensesInTheseDate(@PathVariable("date") String date,@PathVariable("id")Long id){
		
		
	return expenseServise.getAllYourExpensesInTheseDate(date,id);
	
	}
	
	
		
		
		
		
			
	

}
