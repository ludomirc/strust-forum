package com.qbit.forum.model;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(table = "users", name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(table = "roles", name = "role_id", referencedColumnName = "id")
            }
    )
    protected Collection<Role> roles;

    public User() {
    }

    public User(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
