package com.api.expense.repository;

import com.api.expense.model.UserInfo;
import com.api.expense.model.UserRolesTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRolesRepository extends JpaRepository<UserRolesTbl, Integer> {

    @Query(name = "findByUserNameAndEnabled", nativeQuery = true)
    UserInfo findByUserNameAndEnabled(@Param("userName") String userName);
}
