package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "user", schema = "", catalog = "")
public class UserEntitiy {

    private Integer userID;
    private String name;
    private String type;

    public UserEntitiy() {
    }

    @Id
    @Basic
    @Column(name = "pk_user")
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Basic
    @Column(name = "user_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
