package com.api.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String dob;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String contact_number;
    private String city;
}
