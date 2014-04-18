package org.joyce.webtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Administrator on 14-4-10.
 */

@Entity
@Table(name = "card", schema = "", catalog = "")
public class CardEntity {
    @Id
    @Column(name = "pk_card")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String cardId;

    @Transient
    private Integer userID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_userId")
    private IndividualEntity individualEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Transient
    private String html;

//    @Transient
//    private boolean success;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public IndividualEntity getIndividualEntity() {
        return individualEntity;
    }

    public void setIndividualEntity(IndividualEntity individualEntity) {
        this.individualEntity = individualEntity;
    }

    public Integer getUserID() {
        return individualEntity.getUserID();
    }

    public void setUserID(Integer userId) {
        this.userID = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }

    public void generateHtml(){
        //StringBuilder stringBuilder = new StringBuilder();

        String businessCardTemplate = "<span><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        //String businessCardTemplate = "<span><b>???</b><br><img src=$$$ alt=\"Error\" width=\"200\" height=\"100\"></span>";
        String businessCard = businessCardTemplate.replace("$$$", this.image);
        //String businessCard = businessCardTemplate.replace("???", this.name).replace("$$$", this.card);

        this.setHtml(businessCard);
    }
}
