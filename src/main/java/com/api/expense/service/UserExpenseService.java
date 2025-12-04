package com.api.expense.service;

import com.api.expense.dto.ExpenseDetailsDTO;
import com.api.expense.dto.UserExpenseRequestDTO;
import com.api.expense.dto.UserExpenseResponseDTO;
import com.api.expense.model.ExpDetail;
import com.api.expense.model.ExpItemTbl;
import com.api.expense.model.UsrTbl;
import com.api.expense.repository.ExpenseDetailsRepository;
import com.api.expense.model.UserExpTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserExpenseService {
	
	@Autowired
	private ExpenseDetailsRepository expenseDetailsRepository;
	private String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private SimpleDateFormat sdfSource = new SimpleDateFormat(DATE_FORMAT);
	DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Optional<List<UserExpenseResponseDTO>> getUserExpenses() {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<ExpDetail> expDetails = expenseDetailsRepository.findByUserId(1);
		List<UserExpenseResponseDTO> userExpenseResponseDTOs = null;
		AtomicInteger counter = new AtomicInteger(0);
		if(expDetails.size() > 0) {
				userExpenseResponseDTOs = expDetails.stream().map(expDetail -> 
				new UserExpenseResponseDTO(counter.getAndIncrement(),
						expDetail.getExpId(), expDetail.getUserExpTbl().getUsrTbl().getUserId(),
						expDetail.getExpName(), expDetail.getExpDescription(), expDetail.getExpAmount(),
						expDetail.getIsPaid(), expDetail.getPaidBy(), expDetail.getPaidTo(),
						expDetail.getPayTransNumber(), "", "", "", expDetail.getPartiallyPaid(),
						expDetail.getPartialPaidAmt(), expDetail.getPartialAmtLeft()))
						.filter(Objects::nonNull).collect(Collectors.toList());
		}
		return Optional.ofNullable(userExpenseResponseDTOs);
	}
	
	public Optional<ExpenseDetailsDTO> getUserExpenseById(Integer expId) {
		ExpenseDetailsDTO expDetail = expenseDetailsRepository.getExpenseById(expId);
		return Optional.of(expDetail);
	}

	public Optional<UserExpenseResponseDTO> saveUserExpense(UserExpenseRequestDTO userExpenseRequestDTO) {
		UserExpenseResponseDTO userExpenseResponseDTO = null;
		try {
			ExpDetail expDetail = mapUserExpenseData(userExpenseRequestDTO);
			ExpDetail response = expenseDetailsRepository.save(expDetail);
			userExpenseResponseDTO = new UserExpenseResponseDTO(1,
					response.getUserExpTbl().getExpItemTbl().getExpItemId(),
					response.getUserExpTbl().getUsrTbl().getUserId(),
					response.getExpName(),
					response.getExpDescription(),
					response.getExpAmount(),
					response.getIsPaid(),
					response.getPaidBy(),
					response.getPaidTo(),
					response.getPayTransNumber(),
					response.getExpDate().toString(),
					response.getDueDate().toString(),
					response.getSubmissionDate().toString(),
					response.getPartiallyPaid(),
					response.getPartialAmtLeft(),
					response.getPartialPaidAmt());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.of(userExpenseResponseDTO);
	}

	private ExpDetail mapUserExpenseData(UserExpenseRequestDTO userExpenseRequestDTO) throws ParseException {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ExpItemTbl expItemTbl = new ExpItemTbl();
		expItemTbl.setExpItemId(userExpenseRequestDTO.getExpItemID());
		
		UsrTbl usrTbl = new UsrTbl();
		usrTbl.setUserId(1);
		
		UserExpTbl userExpTbl = new UserExpTbl();
		userExpTbl.setExpItemTbl(expItemTbl);
		userExpTbl.setUsrTbl(usrTbl);
		
		ExpDetail expDetail = new ExpDetail();
		expDetail.setUserExpTbl(userExpTbl);
		expDetail.setUsrTbl(usrTbl);
		expDetail.setExpName(userExpenseRequestDTO.getExpName());
		expDetail.setExpAmount(userExpenseRequestDTO.getExpAmount());
		expDetail.setIsPaid(userExpenseRequestDTO.getIsPaid());
		expDetail.setPartiallyPaid(userExpenseRequestDTO.getPartialPaid());
		expDetail.setPartialPaidAmt(userExpenseRequestDTO.getPartialAmtPaid());
		expDetail.setPartialAmtLeft(userExpenseRequestDTO.getPartialAmtLeft());
		expDetail.setExpDate(Date.valueOf(targetFormat.format(sdfSource.parse(userExpenseRequestDTO.getExpDate()))));
		expDetail.setDueDate(Date.valueOf(targetFormat.format(sdfSource.parse(userExpenseRequestDTO.getDueDate()))));
		expDetail.setSubmissionDate(Date.valueOf(targetFormat.format(sdfSource.parse(userExpenseRequestDTO.getSubmitDate()))));
		expDetail.setPaidBy(userExpenseRequestDTO.getPaidBy());
		expDetail.setPaidTo(userExpenseRequestDTO.getPaidTo());
		expDetail.setPayTransNumber(userExpenseRequestDTO.getPayTransNumber());
		expDetail.setExpDescription(userExpenseRequestDTO.getExpDescription());
		
		return expDetail;
	}
	
	
	
}
