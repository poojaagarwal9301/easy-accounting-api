package com.api.expense.controller;

import com.api.expense.dto.UserDetailsDTO;
import com.api.expense.dto.UserExpenseRequestDTO;
import com.api.expense.dto.UserExpenseResponseDTO;
import com.api.expense.dto.UserRequestDTO;
import com.api.expense.model.ExpItemTbl;
import com.api.expense.model.UsrTbl;
import com.api.expense.repository.ExpenseCategoryRepository;
import com.api.expense.service.UserSevice;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @GetMapping("/")
    public List<UserDetailsDTO> getAllUsers() throws Exception {
        Optional<List<UserDetailsDTO>> userdetails = userSevice.getUsers();
        if(userdetails.isPresent()) {
            return userdetails.get();
        } else {
            throw new Exception();
        }
    }

    @GetMapping("/roles")
    public List<UserDetailsDTO> getAllUserswithRoles() throws Exception {
        Optional<List<UserDetailsDTO>> userdetails = userSevice.getUserWithRoles();
        if(userdetails.isPresent()) {
            return userdetails.get();
        } else {
            throw new Exception();
        }
    }

    @PostMapping
    public ResponseEntity<UsrTbl> addUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        Optional<UsrTbl> response = userSevice.addUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.get());
    }
}
