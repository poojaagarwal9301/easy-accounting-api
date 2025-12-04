package com.api.expense.model;

import java.io.Serializable;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the user_roles_tbl database table.
 * Maps user to roles he is assigned like admin or user
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user_roles_tbl")
@NamedQuery(name="UserRolesTbl.findAll", query="SELECT u FROM UserRolesTbl u")

@SqlResultSetMapping(
		name="userDetailsMapping",
		classes={
				@ConstructorResult(
						targetClass= UserInfo.class,
						columns={
								@ColumnResult(name="userId"),
								@ColumnResult(name="userName"),
								@ColumnResult(name="password"),
								@ColumnResult(name="role"),
								@ColumnResult(name="enabled")
						}
				)
		}
)

@NamedNativeQuery(name="findByUserNameAndEnabled", query="select u.user_id as userId, u.user_name as userName, " +
		"u.password, r.role_name as role, ur.enabled\n" +
		"from user_roles_tbl ur inner join usr_tbl u on u.user_id = ur.user_id\n" +
		"inner join role_tbl r on ur.role_id = r.role_id where u.user_name = :userName " +
		"and ur.enabled=1;", resultSetMapping="userDetailsMapping")

public class UserRolesTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mapping_id")
	private int mappingId;

	//uni-directional many-to-one association to UsrTbl
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="user_id")
	private UsrTbl usrTbl;

	//uni-directional many-to-one association to RoleTbl
	@ManyToOne
	@JoinColumn(name="role_id")
	private RoleTbl roleTbl;

	private byte enabled;

}