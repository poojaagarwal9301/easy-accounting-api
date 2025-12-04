package com.api.expense.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private String role;
    private Byte enabled;

    public UserInfo() {}

    public UserInfo(Integer userId, String userName, String password, String role, Byte enabled) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the id to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format("UserInfo [id=%s, userName=%s, password=%s, role=%s, enabled=%s]", userId, userName, password,
                role, enabled);
    }

}