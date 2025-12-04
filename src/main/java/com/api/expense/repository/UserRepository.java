package com.api.expense.repository;

import com.api.expense.dto.ExpenseDetailsDTO;
import com.api.expense.dto.UserDetailsDTO;
import com.api.expense.model.UserRolesTbl;
import com.api.expense.model.UsrTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UsrTbl, Integer> {


    @Query(name = "getUserWithRole", nativeQuery = true)
    List<UserDetailsDTO> getUserWithRole();
}
