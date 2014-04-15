package org.joyce.webtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "user", schema = "", catalog = "")
public class UserEntity {

    @Id
    @Column(name = "pk_user")
    private Integer userID;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = {CascadeType.ALL} )
    private List<CardEntity> cardList = new ArrayList<>();

    @Transient
    private String repeatUserPassword;

    @Transient
    private boolean success;

    public UserEntity() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CardEntity> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardEntity> cardList) {
        this.cardList = cardList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRepeatUserPassword() {
        return repeatUserPassword;
    }

    public void setRepeatUserPassword(String repeatUserPassword) {
        this.repeatUserPassword = repeatUserPassword;
    }
}
