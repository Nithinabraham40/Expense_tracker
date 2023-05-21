 package com.tutorial.tracker.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tutorial.tracker.dto.ExpenseDto;
import com.tutorial.tracker.model.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long>{

	
    
	
	@Query(
			value="select * from tbl_expense where fk_user_id=:id",
			
			nativeQuery = true
			
			)
	List<Expense> getallExpenseById(Long id);
      @Modifying
	  @Transactional
	
	  @Query(value="delete from tbl_expense where date=:date and fk_user_id=:id",
	  nativeQuery = true)
	
	void deleteByDateAndId(String date, Long id);
      
      

  	@Query(
  			value="select * from tbl_expense where date=:date and fk_user_id=:id",
  			
  			nativeQuery = true
  			
  			)
      
	List<Expense> getallExpensesBydate(String date,Long id);
  	
  	
  	 @Query(value="SELECT * FROM tbl_expense WHERE fk_user_id=:userId and date LIKE %:month%",nativeQuery = true)
	List<Expense> getResultsFromTableExpense(Long userId,@Param("month") String month);
	

}
