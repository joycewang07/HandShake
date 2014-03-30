package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "user", schema = "", catalog = "")
public class UserEntity {

    @Id
    @Column(name = "pk_user")
    private Integer userID;
    @Column(name = "user_name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "user_type")
    private String type;

    public UserEntity() {
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
