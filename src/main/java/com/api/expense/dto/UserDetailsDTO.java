package com.api.expense.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsDTO {

    private Date dob;
    private int userId;
    private String name;
    private String userName;
    private String access;

}
