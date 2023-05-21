package com.tutorial.tracker.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.tracker.Repository.ExpenseRepo;
import com.tutorial.tracker.Repository.UserRepository;
import com.tutorial.tracker.dto.ExpenseDto;
import com.tutorial.tracker.model.Expense;
import com.tutorial.tracker.model.User;


@Service
public class ExpenseService {
	
	
	@Autowired
	private ExpenseRepo expenseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	
    @Transactional
	public ResponseEntity<String> addExpenses(Long userId, ExpenseDto expenseDto) {
		
		//find user by user id
		Optional<User> findUser=userRepo.findById(userId);
		//if not found return
		if(!findUser.isPresent()) {
			return new ResponseEntity<String>("userid not found",HttpStatus.BAD_REQUEST);
		}
		
		
		//save user
		User user=findUser.get();
		
		//add all from dto to expense
		Expense expense=Expense.builder().description(expenseDto.getDescription()).date(LocalDate.now()).time(LocalTime.now())
				.price(expenseDto.getPrice()).title(expenseDto.getTitle()).user(user).build();
		//save in database
		expenseRepo.save(expense);
		
		return new ResponseEntity<String>("added your expense",HttpStatus.OK);
	}



	public List<ExpenseDto>getallExpense(Long id) {
		
		List<Expense>allexpense=expenseRepo.getallExpenseById(id);
		
		//need to return result us ExpenseDto
		//creating a new list to return 
		
		List<ExpenseDto>allExpenseinDto=convertExpenselistToExpenseDto(allexpense);
		
		return allExpenseinDto;
		
		
		
	}

	//to map one list to another list
	public List<ExpenseDto>convertExpenselistToExpenseDto(List<Expense>expenseList){
		

		List<ExpenseDto>allexpenseinDto=new ArrayList<>();
		for(Expense expense:expenseList) {
			//go throught each expense and fill new list
			ExpenseDto expensedto=ExpenseDto.builder().date(expense.getDate()).description(expense.getDescription())
					.price(expense.getPrice()).time(expense.getTime()).title(expense.getTitle()).build();
			
			allexpenseinDto.add(expensedto);
			
		}
		
		return allexpenseinDto;
		
	}


	
	
	public ResponseEntity<String> deleteResponse(String date, Long id) {
	
		expenseRepo.deleteByDateAndId(date,id);
		
		
		return new ResponseEntity<String>("Sucessfully deleted",HttpStatus.OK);
	}



	public List<ExpenseDto> getAllYourExpensesInTheseDate(String date,Long id) {
		
		List<Expense>listOfExpenses=expenseRepo.getallExpensesBydate(date,id);
		
		List<ExpenseDto>allListinExpenseDto=convertExpenselistToExpenseDto(listOfExpenses);
		
		
		return allListinExpenseDto;
	}



	public List<Expense> getExpenseResults(Long userId,String month) {
		
		return expenseRepo.getResultsFromTableExpense(userId,month);
	}
	
	
    
    

}
