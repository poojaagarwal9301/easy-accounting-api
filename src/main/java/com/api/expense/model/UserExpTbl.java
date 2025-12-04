package com.api.expense.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the user_exp_tbl database table.
 * Stores user expense - primary table 
 * secondary table where more details about this expense is stored is ExpDetail
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user_exp_tbl")
@NamedQuery(name="UserExpTbl.findAll", query="SELECT u FROM UserExpTbl u")
public class UserExpTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_item_id")
	private int userItemId;

	@Column(name="active", insertable = false)
	private byte active;

	@Column(name="created_on", insertable = false)
	private Timestamp createdOn;

	@Column(name="updated_on", insertable = false)
	private Timestamp updatedOn;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UsrTbl usrTbl;

	@ManyToOne
	@JoinColumn(name="exp_item_id")
	private ExpItemTbl expItemTbl;

}