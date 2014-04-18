package org.joyce.webtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "activity", schema = "", catalog = "")
public class ActivityEntity {
    @Id
    @Column(name = "pk_activity")
    private Integer activityId;
    @Column(name = "activity_name")
    private String name;
    @Column(name = "activity_date")
    private String date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_company")
    private CompanyEntity companyEntity;
    @Column(name = "activity_industry")
    private String industry;


    public ActivityEntity() {
    }


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
