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

    public Role() {
    }

    public Role(String name, String description, short roleId, Collection<Privilege> privileges) {
        this.name = name;
        this.description = description;
        this.roleId = roleId;
        this.privileges = privileges;
    }

    public Role(Long id, String name, String description, short roleId, Collection<Privilege> privileges) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roleId = roleId;
        this.privileges = privileges;
    }

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

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
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
