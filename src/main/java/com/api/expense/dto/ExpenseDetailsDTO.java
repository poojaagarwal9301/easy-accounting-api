package com.api.expense.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseDetailsDTO {

    private String expType;
    private String expName;
    private String expDesc;
    private BigDecimal expAmount;
    private Date receivedDate;
    private Date dueDate;
    private Date submissionDate;
    private Boolean isPaid;
    private Boolean partialPaid;
    private BigDecimal partialAmountPaid;
    private BigDecimal partialAmountLeft;
    private String paidBy;
    private String paidTo;
    private String payTransNumber;

    public ExpenseDetailsDTO(String expType, String expName, String expDesc, BigDecimal expAmount, Date receivedDate, Date dueDate, Date submissionDate, Boolean isPaid, Boolean partialPaid, BigDecimal partialAmountPaid, BigDecimal partialAmountLeft, String paidBy, String paidTo, String payTransNumber) {
        this.expType = expType;
        this.expName = expName;
        this.expDesc = expDesc;
        this.expAmount = expAmount;
        this.receivedDate = receivedDate;
        this.dueDate = dueDate;
        this.submissionDate = submissionDate;
        this.isPaid = isPaid;
        this.partialPaid = partialPaid;
        this.partialAmountPaid = partialAmountPaid;
        this.partialAmountLeft = partialAmountLeft;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.payTransNumber = payTransNumber;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getExpDesc() {
        return expDesc;
    }

    public void setExpDesc(String expDesc) {
        this.expDesc = expDesc;
    }

    public BigDecimal getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(BigDecimal expAmount) {
        this.expAmount = expAmount;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getPartialPaid() {
        return partialPaid;
    }

    public void setPartialPaid(Boolean partialPaid) {
        this.partialPaid = partialPaid;
    }

    public BigDecimal getPartialAmountPaid() {
        return partialAmountPaid;
    }

    public void setPartialAmountPaid(BigDecimal partialAmountPaid) {
        this.partialAmountPaid = partialAmountPaid;
    }

    public BigDecimal getPartialAmountLeft() {
        return partialAmountLeft;
    }

    public void setPartialAmountLeft(BigDecimal partialAmountLeft) {
        this.partialAmountLeft = partialAmountLeft;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getPayTransNumber() {
        return payTransNumber;
    }

    public void setPayTransNumber(String payTransNumber) {
        this.payTransNumber = payTransNumber;
    }
}
