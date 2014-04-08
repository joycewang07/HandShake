package org.joyce.webtool.model;

import org.eclipse.jetty.server.Authentication;

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

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private String type;

    @Column(name = "card")
    private String card;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Transient
    private String html;

    @Transient
    private boolean update;

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

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void generateHtml(){
        //StringBuilder stringBuilder = new StringBuilder();

        String businessCardTemplate = "<span><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        //String businessCardTemplate = "<span><b>???</b><br><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        String businessCard = businessCardTemplate.replace("$$$", this.card);
        //String businessCard = businessCardTemplate.replace("???", this.name).replace("$$$", this.card);

        this.setHtml(businessCard);
    }


}
