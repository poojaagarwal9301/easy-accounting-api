package com.api.expense.repository;

import com.api.expense.model.ExpDetail;
import com.api.expense.dto.ExpenseDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ExpenseDetailsRepository extends JpaRepository<ExpDetail, Integer> {

	@Query(value="select * from exp_details ed inner join user_exp_tbl ue on ue.user_item_id = ed.user_item_id where ue.user_item_id = :id", nativeQuery=true)
	ExpDetail findByUserItemId(@Param("id") int id);

	@Query(value="select ed.* from exp_details ed inner join usr_tbl ut on ut.user_id=ed.user_id where ut.user_id = :id", nativeQuery=true)
	List<ExpDetail> findByUserId(@Param("id") int id);

	@Query(name = "getExpenseById", nativeQuery = true)
	ExpenseDetailsDTO getExpenseById(@Param("expId") Integer expId);
	
	
}
