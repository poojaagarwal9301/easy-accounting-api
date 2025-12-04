package com.api.expense.model;

import java.io.Serializable;
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
 * The persistent class for the exp_doc database table.
 * Supporting documents with expense
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="exp_doc")
@NamedQuery(name="ExpDoc.findAll", query="SELECT e FROM ExpDoc e")
public class ExpDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="exp_doc_id")
	private int expDocId;

	private byte active;

	@Column(name="exp_doc_desc")
	private int expDocDesc;

	@Temporal(TemporalType.DATE)
	@Column(name="exp_doc_name")
	private Date expDocName;

	@Column(name="exp_doc_path")
	private String expDocPath;

	//uni-directional many-to-one association to ExpDetail
	@ManyToOne
	@JoinColumn(name="exp_id")
	private ExpDetail expDetail;

}