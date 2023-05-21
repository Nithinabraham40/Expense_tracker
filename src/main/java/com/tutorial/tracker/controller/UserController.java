package com.tutorial.tracker.controller;

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

import com.tutorial.tracker.dto.ExpenseDto;
import com.tutorial.tracker.dto.SignInInput;
import com.tutorial.tracker.dto.SignInOutput;
import com.tutorial.tracker.dto.SignUpInput;
import com.tutorial.tracker.dto.SignUpOutput;
import com.tutorial.tracker.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/signup")
	
	public ResponseEntity<SignUpOutput>signup(@RequestBody SignUpInput signupinput){
		
		
		
		return userService.signUp(signupinput);
		
	}
	
	@PostMapping("/signin")
	
	public ResponseEntity<SignInOutput>signIn(@RequestBody SignInInput  signininput){
		
		
		return userService.signIn(signininput);
	}
	
	@DeleteMapping("/signout/{token}/{email}")
	
	public ResponseEntity<String>signOut(@PathVariable("token")String token,@PathVariable("email") String email){
		
		return userService.signOut(token,email);
	}
	//add his expenses
	
	@PostMapping("add/expense/{token}/{email}")
	
	public ResponseEntity<String>addExpense(@PathVariable("token") String token ,@PathVariable("email") String email,@RequestBody ExpenseDto expenseDto){
		
		
		return userService.addExpense(token,email,expenseDto);
	}
	
	
	//to get his expenses
	@GetMapping("allexpense/{token}/{email}")
	
	public List<ExpenseDto>getallExpense(@PathVariable("token") String token,@PathVariable("email") String email){
		
		return userService.getallExpense(token,email);
	}
	
	//get expense at this date
	@GetMapping("expense/date/{date}/{token}/{email}")
	
	public List<ExpenseDto>getallExpense(@PathVariable("date")String date, @PathVariable("token")String token,@PathVariable("email")String email){
		
		return userService.getExpenseByDate(date,token,email);
	}
	
	@GetMapping("expence/month/{month}/{token}/{email}")
	
	public ResponseEntity<Double> mouthlyExpense(@PathVariable("month") String month,@PathVariable("token") String token,@PathVariable("email") String email) {
		
		
		return  userService.expenceOfMounth(month,token,email);
	}

	
	
}
