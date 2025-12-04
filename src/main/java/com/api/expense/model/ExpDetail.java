package com.api.expense.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.api.expense.dto.ExpenseDetailsDTO;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.TrueFalseConverter;
import org.hibernate.type.YesNoConverter;


/**
 * The persistent class for the exp_details database table.
 * Stores details about expense paid or to be paid
 * 
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="exp_details")
@NamedQuery(name="ExpDetail.findAll", query="SELECT e FROM ExpDetail e")


@SqlResultSetMapping(
		name="expenseDetailsMapping",
		classes={
				@ConstructorResult(
						targetClass= ExpenseDetailsDTO.class,
						columns={
								@ColumnResult(name="expType"),
								@ColumnResult(name="expName"),
								@ColumnResult(name="expDesc"),
								@ColumnResult(name="expAmount"),
								@ColumnResult(name="receivedDate"),
								@ColumnResult(name="dueDate"),
								@ColumnResult(name="submissionDate"),
								@ColumnResult(name="isPaid"),
								@ColumnResult(name="partialPaid"),
								@ColumnResult(name="partialAmountPaid"),
								@ColumnResult(name="partialAmountLeft"),
								@ColumnResult(name="paidBy"),
								@ColumnResult(name="paidTo"),
								@ColumnResult(name="payTransNumber")
						}
				)
		}
)

@NamedNativeQuery(name="getExpenseById", query = "select ei.item_name as expType, e.exp_name as expName, e.exp_description as expDesc, " +
		"e.exp_amount as expAmount, e.exp_date as receivedDate, e.due_date as dueDate, e.submission_date as submissionDate, " +
		"e.is_paid as isPaid, e.partially_paid as partialPaid, e.partial_paid_amt as partialAmountPaid, " +
		"e.partial_amt_left as partialAmountLeft, e.paid_by as paidBy, e.paid_to as paidTo, e.pay_trans_number as payTransNumber " +
		"from exp_details e\n" +
		"inner join user_exp_tbl u on e.user_item_id = u.user_item_id inner join exp_item_tbl ei on " +
		"ei.exp_item_id = u.exp_item_id where e.exp_id = :expId", resultSetMapping="expenseDetailsMapping")
public class ExpDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="exp_id")
	private int expId;

	@Column(name="active", insertable = false)
	private byte active;

	@Column(name="created_on", insertable = false)
	private Timestamp createdOn;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="exp_amount")
	private BigDecimal expAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="exp_date")
	private Date expDate;

	@Column(name="exp_description")
	private String expDescription;
	
	@Column(name="exp_name")
	private String expName;
	
	@Column(name="is_paid", columnDefinition = "TINYINT(1)")
	@Convert(converter = NumericBooleanConverter.class)
	public Boolean isPaid = false;
	
	@Column(name="paid_by")
	private String paidBy;
	
	@Column(name="paid_to")
	private String paidTo;
	
	@Column(name="pay_trans_number")
	private String payTransNumber;
	
	@Column(name="partial_amt_left")
	private BigDecimal partialAmtLeft;
	
	@Column(name="partially_paid", columnDefinition = "TINYINT(1)")
	@Convert(converter = NumericBooleanConverter.class)
	public Boolean partiallyPaid = false;
	
	@Column(name="partial_paid_amt")	
	private BigDecimal partialPaidAmt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="submission_date")
	private Date submissionDate;

	@Column(name="updated_on", insertable = false)
	private Timestamp updatedOn;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UsrTbl usrTbl;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_item_id")
	private UserExpTbl userExpTbl;

}