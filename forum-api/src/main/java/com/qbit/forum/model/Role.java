package com.qbit.forum.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Benek on 2015-07-15.
 */
@Entity
@Table(name = "roles")
public class Role extends BaseModel {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "role_id")
    protected short roleId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_privilege",
            joinColumns = {
                    @JoinColumn(table = "role", name = "role_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(table = "privilege", name = "privilege_id", referencedColumnName = "id")
            }
    )
    protected Collection<Privilege> privileges;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getRoleId() {
        return roleId;
    }

    public void setRoleId(short roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
