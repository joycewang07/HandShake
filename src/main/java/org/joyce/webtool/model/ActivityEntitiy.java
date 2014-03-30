package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "activity", schema = "", catalog = "")
public class ActivityEntitiy {
    private Integer activityID;
    private String organizer;
    private String industry;


    public ActivityEntitiy() {
    }


    @Id
    @Basic
    @Column(name = "pk_activity")
    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    @Basic
    @Column(name = "activity_organizer")
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Basic
    @Column(name = "activity_industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
