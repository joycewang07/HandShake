package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by junyan Zhang on 14-3-30.
 */
@Entity
@Table(name = "relationship")
public class Relationship {
    @Id
    @Column(name = "pk_relationship")
    private Integer pkRelationship;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_activity")
    private ActivityEntity activityEntity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user1")
    private UserEntity userEntity1;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user2")
    private UserEntity userEntity2;

    public Integer getPkRelationship() {
        return pkRelationship;
    }

    public void setPkRelationship(Integer pkRelationship) {
        this.pkRelationship = pkRelationship;
    }

    public ActivityEntity getActivityEntity() {
        return activityEntity;
    }

    public void setActivityEntity(ActivityEntity activityEntity) {
        this.activityEntity = activityEntity;
    }

    public UserEntity getUserEntity1() {
        return userEntity1;
    }

    public void setUserEntity1(UserEntity userEntity1) {
        this.userEntity1 = userEntity1;
    }

    public UserEntity getUserEntity2() {
        return userEntity2;
    }

    public void setUserEntity2(UserEntity userEntity2) {
        this.userEntity2 = userEntity2;
    }
}
