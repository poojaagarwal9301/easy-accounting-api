package com.api.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class UserExpenseRequestDTO {

	private Integer userID;
	private Integer expItemID;
	private String expName;
	private BigDecimal expAmount;
	private Boolean isPaid;
	private String paidBy;
	private String paidTo;
	private String payTransNumber;
	private String expDate;
	private String dueDate;
	private String submitDate;
	private String expDescription;
	private Boolean partialPaid;
	private BigDecimal partialAmtPaid;
	private BigDecimal partialAmtLeft;


}
