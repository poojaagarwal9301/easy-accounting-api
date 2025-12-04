package com.api.expense.controller;

import java.util.List;
import java.util.Optional;

import com.api.expense.model.ExpItemTbl;
import com.api.expense.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	@Autowired
	private ExpenseCategoryRepository expenseCategoryRepository;
	
	
	@GetMapping("/")
	public List<ExpItemTbl> getAllExpenseType() {
	    return expenseCategoryRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ExpItemTbl> getExpenseTypeById(@PathVariable int id) {
	    Optional<ExpItemTbl> exp = expenseCategoryRepository.findById(id);
	    return exp.map(response -> ResponseEntity.ok().body(response))
	    		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}

