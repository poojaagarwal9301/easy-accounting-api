package com.api.expense.controller;

import com.api.expense.dto.ExpenseDetailsDTO;
import com.api.expense.dto.UserExpenseRequestDTO;
import com.api.expense.dto.UserExpenseResponseDTO;
import com.api.expense.service.UserExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserExpenseController {

	@Autowired
	private UserExpenseService userExpenseService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserExpenseResponseDTO>> getAllUserExpenses() {
		Optional<List<UserExpenseResponseDTO>> res = userExpenseService.getUserExpenses();
	    return res.map(response -> ResponseEntity.ok().body(response))
	    		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/exp/{expId}")
	public ResponseEntity<ExpenseDetailsDTO> getUserExpenseByExpenseId(@PathVariable Integer expId) {
		Optional<ExpenseDetailsDTO> res = userExpenseService.getUserExpenseById(expId);
	    return res.map(response -> ResponseEntity.ok().body(response))
	    		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/")
	public ResponseEntity<UserExpenseResponseDTO> saveUserExpense(@RequestBody UserExpenseRequestDTO userExpenseRequestDTO) {
		Optional<UserExpenseResponseDTO> res = userExpenseService.saveUserExpense(userExpenseRequestDTO);
	    return res.map(response -> ResponseEntity.ok().body(response))
	    		.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
}
