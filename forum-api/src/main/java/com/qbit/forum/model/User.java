package com.qbit.forum.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Benek on 2015-07-15.
 */
@Entity
public class User extends BaseModel {

    String username;
    String password;


}
