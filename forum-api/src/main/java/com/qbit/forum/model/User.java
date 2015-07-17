package com.qbit.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Benek on 2015-07-15.
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
