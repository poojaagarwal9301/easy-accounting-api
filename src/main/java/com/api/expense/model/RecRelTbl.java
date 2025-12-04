package com.api.expense.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the rec_rel_tbl database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="rec_rel_tbl")
@NamedQuery(name="RecRelTbl.findAll", query="SELECT r FROM RecRelTbl r")
public class RecRelTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_map_id")
	private int recMapId;

	private int depth;

	//uni-directional many-to-one association to RecItemTbl
	/*@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ancestor_id", referencedColumnName="rec_item_id"),
		@JoinColumn(name="descendant_id", referencedColumnName="rec_item_id")
		})
	private RecItemTbl recItemTbl;*/

}