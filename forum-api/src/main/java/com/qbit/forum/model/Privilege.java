package com.qbit.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Benek on 2015-07-15.
 */
@Entity
@Table(name = "privileges")
public class Privilege extends BaseModel {

    @Column(name = "privilege_id")
    protected Short privilegeId;

    @Column(name = "description")
    protected String description;

    public Privilege() {
    }

    public Privilege(Short privilegeId, String description) {
        this.privilegeId = privilegeId;
        this.description = description;
    }

    public Privilege(Long id, short privilegeId, String description) {
        this.id = id;
        this.privilegeId = privilegeId;
        this.description = description;
    }

    public Short getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Short privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id='" + getId() + '\'' +
                ", privilegeId=" + privilegeId +
                ", description='" + description + '\'' +
                '}';
    }
}
