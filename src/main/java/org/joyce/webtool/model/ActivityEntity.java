package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-29.
 */
@Entity
@Table(name = "activity", schema = "", catalog = "")
public class ActivityEntity {
    @Id
    @Column(name = "pk_activity")
    private Integer activityId;
    @Column(name = "activity_organizer")
    private String organizer;
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



    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }



    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
