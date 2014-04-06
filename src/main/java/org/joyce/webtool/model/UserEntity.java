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

    @Column(name = "user_name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private String type;

    @Column(name = "user_card")
    private String card;

    @Transient
    private String html;

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

    public void generateHtml(){
        //StringBuilder stringBuilder = new StringBuilder();

        String businessCardTemplate = "<span><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        //String businessCardTemplate = "<span><b>???</b><br><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        String businessCard = businessCardTemplate.replace("$$$", this.card);
        //String businessCard = businessCardTemplate.replace("???", this.name).replace("$$$", this.card);

        this.setHtml(businessCard);
    }
}
