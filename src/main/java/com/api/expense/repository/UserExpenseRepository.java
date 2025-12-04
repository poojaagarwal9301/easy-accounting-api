package com.api.expense.repository;

import com.api.expense.model.UserExpTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserExpenseRepository extends JpaRepository<UserExpTbl, Integer> {

}
