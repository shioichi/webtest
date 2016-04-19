package com.cpj.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by chenpengjiang on 2016/4/18.
 */
@Entity
@Table(name = "sec_user", schema = "xz_cloud_sys", catalog = "")
public class SecUser implements Serializable{
    private int userId;
    private String userName;
    private String password;
    private String projectname;
    private Timestamp createdTime;
    private Timestamp updateTime;
    private String rolename;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "projectname")
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecUser secUser = (SecUser) o;

        if (userId != secUser.userId) return false;
        if (userName != null ? !userName.equals(secUser.userName) : secUser.userName != null) return false;
        if (password != null ? !password.equals(secUser.password) : secUser.password != null) return false;
        if (projectname != null ? !projectname.equals(secUser.projectname) : secUser.projectname != null) return false;
        if (createdTime != null ? !createdTime.equals(secUser.createdTime) : secUser.createdTime != null) return false;
        if (updateTime != null ? !updateTime.equals(secUser.updateTime) : secUser.updateTime != null) return false;
        if (rolename != null ? !rolename.equals(secUser.rolename) : secUser.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (projectname != null ? projectname.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }
}
