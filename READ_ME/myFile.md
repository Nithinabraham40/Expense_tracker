# Welcome to readme-md-generator &#x1F44B; 
![example workflow](https://img.shields.io/badge/Eclipse-Version%3A%202022--09%20(4.25.0)-orange)
![example workflow](https://img.shields.io/badge/SpringBoot-2.2.1-yellowgreen)
![example workflow](https://img.shields.io/badge/Java-8-yellowgreen)
![example workflow](https://img.shields.io/badge/Postman-v10.13-orange)
![example workflow](https://img.shields.io/badge/Documentation-Yes-green)
![example workflow](https://img.shields.io/badge/Manitained%3F-Yes-green)
 >CLI that generate beautiful **ReadME**.md files

  :house:  <b><span style="color: blue;">Homepage</span></b>
  


 # Prerequisties

 - **Eclipse >=4.55.0**
 - **Postman >=10.13**
 


# Install
```
Maven Install
SpringTool Install
```
 # Framework And Language

 - **Framework :  SpringBoot**
 - **Language :  Java**

 # Dependencies Required

 
 - **spring-boot-starter-web**
 - **spring-boot-devtools**
 - **spring-boot-starter-data-jpa**
 - **mysql-connector**
 - **lambok**
 - **jbcrypt**

 - **spring-boot-starter-test**
 


# Models Used



 - **User**
 -  **Expense**
 -  **Authentication**
 


	
	



#  Data flow

- **User send a request to ApI endpoint**
- **api forward it to the controller**
- **controller forward it to the Service layer**
- **service layer provide the necessary buciness logic and ask the repository for data**
- **Repository fetch the data from Mysql and give it back to service layer**
- **service layer give it to controller**
- **contoller give it to api**
- **Api give the response to user**


#  Api end points used at User Controller

- **user/signin**
- **user/signup**
- **user/signout/{token}/{email}**
- **user/add/expense/{token}/{email}**
- **user/allexpense/{token}/{email}**
- **user/expense/date/{date}/{token}/{email}**
- **user/expence/month/{month}/{token}/{email}**


#  Api end points used at Expense Controller

- **expense/addExpense/userid/{id}**
- **expense/view/expense/userid/{id}**
- **expense/delete/date/{date}/userid/{id}**
- **expense/get/date/{date}/userid/{id}**



#   User Controller
```


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

```



#   Expense Controller
```
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


```








	
	


  


	







	



# DataBase Used

<details>
<summary><b><span style="color: white;">Clickme</span></b> &#x1F4F2; </summary>

*Mysql*





# UserDatabase
 - **userid**
 -  **username**
 -  **useremail**
 -  **userpassword**

# AuthenticationDatabase
 - -**authid**
 -  **token**
 -  **tokencreation**
 -  **fk_userid**

# ExpenseDatabase
 - -**expenseid**
 -  **tittle**
 -  **Description**
 -  **price**
-  **date**
 -  **time**
 -   **fk_userid**





</details>






  




# Summary

 Spring boot expense management project using Mysql us database with proper authentictaions and validations .
These project will have the following features
**User Signup**,
**user signin**,
**user Signout**,
**add expenses**,
**remove expence by id**,
**remove expense by date**
**calculate expanse for any particulat mounth**
**get expense by date**
.






# :handshake:  Contributing
  Contributions,issues and features request are welcome! 
  

  #


  This *README* was generated with &#x2764;&#xFE0F; by <b><span style="color: blue;">readme-md-generator</span></b> 










   
  
  

