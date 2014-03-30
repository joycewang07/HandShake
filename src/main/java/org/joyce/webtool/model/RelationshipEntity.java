package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by junyan Zhang on 14-3-30.
 */
@Entity
@Table(name = "relationship")
public class RelationshipEntity {
    @Id
    @Column(name = "pk_relationship")
    private Integer pkRelationship;

    @Column(name = "fk_activity")
    private Integer activity;

    @Column(name = "fk_user1")
    private Integer user1;

    @Column(name = "fk_user2")
    private Integer user2;

    public Integer getPkRelationship() {
        return pkRelationship;
    }

    public void setPkRelationship(Integer pkRelationship) {
        this.pkRelationship = pkRelationship;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }
}

