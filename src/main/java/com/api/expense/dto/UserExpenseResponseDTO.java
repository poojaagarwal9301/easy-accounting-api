package com.api.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class UserExpenseResponseDTO {

	private Integer id;
	private Integer expId;
	private Integer userID;
	private String expName;
	private String expDescription;
	private BigDecimal expAmount;
	private Boolean isPaid;
	private String paidBy;
	private String paidTo;
	private String payTransNumber;
	private String expDate;
	private String dueDate;
	private String submitDate;
	private Boolean partialPaid;
	private BigDecimal partialAmtPaid;
	private BigDecimal partialAmtLeft;

}
