package com.api.expense.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the rec_details database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="rec_details")
@NamedQuery(name="RecDetail.findAll", query="SELECT r FROM RecDetail r")
public class RecDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_id")
	private int recId;

	private byte active;

	@Column(name="created_on", insertable = false)
	private Timestamp createdOn;

	@Column(name="rec_description")
	private String recDescription;

	@Temporal(TemporalType.DATE)
	@Column(name="rec_generation_date")
	private Date recGenerationDate;

	@Column(name="updated_on", insertable = false)
	private Timestamp updatedOn;

	//uni-directional many-to-one association to UsrTbl
	@ManyToOne
	@JoinColumn(name="user_id")
	private UsrTbl usrTbl;

	//uni-directional many-to-one association to UserRecTbl
	@ManyToOne
	@JoinColumn(name="user_item_id")
	private UserRecTbl userRecTbl;

}