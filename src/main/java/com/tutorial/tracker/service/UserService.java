package com.tutorial.tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.tracker.Repository.AuthenticationRepository;
import com.tutorial.tracker.Repository.UserRepository;
import com.tutorial.tracker.dto.ExpenseDto;
import com.tutorial.tracker.dto.SignInInput;
import com.tutorial.tracker.dto.SignInOutput;
import com.tutorial.tracker.dto.SignUpInput;
import com.tutorial.tracker.dto.SignUpOutput;
import com.tutorial.tracker.model.Authentication;
import com.tutorial.tracker.model.Expense;
import com.tutorial.tracker.model.User;
@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private AuthenticationRepository authrepo;
	
	@Autowired
	private ExpenseService expenseServise;
	
	
	

	public ResponseEntity<SignUpOutput> signUp(SignUpInput signupinput) {
		
		//check user exist with these mail or not
		
		User user=checkUserExit(signupinput.getUserEmail());
		
		if(user!=null) {
			
			return new ResponseEntity<SignUpOutput>(new SignUpOutput("user already exit"),HttpStatus.BAD_REQUEST);
		}
		
		String password=signupinput.getUserPassword();
		String encryptedPassword=encryptPassword(password);
		
		//save user
		
		user=User.builder().userEmail(signupinput.getUserEmail()).userPassword(encryptedPassword).userName(signupinput.getUserName())
				.build();
		
		userRepo.save(user);
		
		
		
		return new ResponseEntity<SignUpOutput>(new SignUpOutput("signup sucessfully"),HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	//to find user with email
	   public User checkUserExit(String email) {
		
		
		User user=userRepo.findByUserEmail(email);
		
		return user;
		
		
	   }
	
	     //encrypt password
	       private String encryptPassword(String password) {
	       
		     String salt = BCrypt.gensalt();

		     
		     String hashedPassword = BCrypt.hashpw(password, salt);

		     return hashedPassword;
		 }

	       //verify password
	       
		   private boolean verifyPassword(String password, String hashedPassword) {
		    return BCrypt.checkpw(password, hashedPassword);
		}







  //signin
		public ResponseEntity<SignInOutput> signIn(SignInInput signininput) {
			User user=checkUserExit(signininput.getUserEmail());
			if(user==null) {
				
				
				return new ResponseEntity<SignInOutput>(new SignInOutput("sign in failed"),HttpStatus.BAD_REQUEST);
			}
			// if user exit
			String password=signininput.getUserPassword();
			String encryptedPassword=user.getUserPassword();
			
			Boolean verifyPassword=verifyPassword(password,encryptedPassword);
			if(verifyPassword==false) {return new ResponseEntity<SignInOutput>(new SignInOutput("sign in failed"),HttpStatus.BAD_REQUEST);}
			
			//if password is correct
			//create token and save
			
			Authentication authentication=new Authentication(user);
			authrepo.save(authentication);
			
			
			//return token
			return new ResponseEntity<SignInOutput>(new SignInOutput(authentication.getToken()),HttpStatus.BAD_REQUEST);
		}









		public ResponseEntity<String> signOut(String token, String email) {
			
			Boolean verifyTokenAndEmail=verify(token,email);
			
			if(verifyTokenAndEmail==false) {
				
				return new ResponseEntity<String>("signout failed",HttpStatus.BAD_REQUEST);
			}
			
			Authentication authentication=authrepo.findByToken(token);
			
			authrepo.delete(authentication);


			
			return new ResponseEntity<String>("signout sucessfully",HttpStatus.OK);
		}









		private Boolean verify(String token, String email) {
			
			
			
			
			
			if(token==null && email==null){
	            return false;
	        }

	        Authentication authToken = authrepo.findbyToken(token);

	        if(authToken==null){
	            return false;
	        }

	        String expectedEmail = authToken.getUser().getUserEmail();


	        return expectedEmail.equals(email);
	    }









		public List<ExpenseDto> getallExpense(String token, String email) {
			//need to verify token and email matches
			
			List<ExpenseDto>allExpense=new ArrayList<>();
			Boolean verifyTokenAndEmail=verify(token,email);
			
			//if fails return empty list
			
			if(verifyTokenAndEmail==false) {
				
				return allExpense;
			}
			// need to get the user
			
			User user=userRepo.findByUserEmail(email);
			//now get his id
			Long userId=user.getUserId();
			
			return expenseServise.getallExpense(userId);
			
			
			
			
			
		}









		public ResponseEntity<String> addExpense(String token,String email,ExpenseDto expenseDto) {
			//need to verify token and email matches
			Boolean verifyTokenAndEmail=verify(token,email);
			//if fails return failed
			if(verifyTokenAndEmail==false) {
				return new ResponseEntity<String>("failed to authenticate",HttpStatus.BAD_REQUEST);
				
			}
			
			//need to get get userid
			User user=userRepo.findByUserEmail(email);
			//now get his id
			Long userId=user.getUserId();
			
			//pass to expenseservise
			return expenseServise.addExpenses(userId, expenseDto);
		}









		public List<ExpenseDto> getExpenseByDate(String date, String token, String email) {
         //need to verify token and email matches
			
			List<ExpenseDto>allExpense=new ArrayList<>();
			Boolean verifyTokenAndEmail=verify(token,email);
			
			//if fails return empty list
			
			if(verifyTokenAndEmail==false) {
				
				return allExpense;
			}
			
			//need to get get userid
			User user=userRepo.findByUserEmail(email);
			//now get his id
			Long userId=user.getUserId();
			
			return expenseServise.getAllYourExpensesInTheseDate(date, userId);
		}









		public ResponseEntity<Double> expenceOfMounth(String month, String token, String email) {
			
			Boolean verifyTokenAndEmail=verify(token,email);
			
			if(verifyTokenAndEmail==false) {
				return new ResponseEntity<Double>(0.0,HttpStatus.BAD_REQUEST);
				
			}
			
			//need to get get userid
			User user=userRepo.findByUserEmail(email);
			//now get his id
			Long userId=user.getUserId();
			
			List<Expense>expenseWithDate=expenseServise.getExpenseResults(userId,month);
			
			Double expenseAnount=FindAmount(expenseWithDate);
			
			return new ResponseEntity<Double>(expenseAnount,HttpStatus.OK);
		}









		private Double FindAmount(List<Expense> expenseWithDate) {
			
			Double ans=0.0;
			for(Expense expense:expenseWithDate) {
				
				ans=ans+expense.getPrice();
				
			}
			
			return ans;
		}









		
		
		
		
		
		
		}
	
	

