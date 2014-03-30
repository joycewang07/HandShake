package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by junyan Zhang on 14-3-30.
 */
@Entity
@Table(name = "relationship", schema = "", catalog = "")
public class RelationshipEntity {
    private Integer pkRelationship;
    private Integer fkActivity;
    private Integer fkUser1;
    private Integer fkUser2;

    @Id
    @Column(name = "pk_relationship")
    public Integer getPkRelationship() {
        return pkRelationship;
    }

    public void setPkRelationship(Integer pkRelationship) {
        this.pkRelationship = pkRelationship;
    }

    @Basic
    @Column(name = "fk_activity")
    public Integer getFkActivity() {
        return fkActivity;
    }

    public void setFkActivity(Integer fkActivity) {
        this.fkActivity = fkActivity;
    }

    @Basic
    @Column(name = "fk_user1")
    public Integer getFkUser1() {
        return fkUser1;
    }

    public void setFkUser1(Integer fkUser1) {
        this.fkUser1 = fkUser1;
    }

    @Basic
    @Column(name = "fk_user2")
    public Integer getFkUser2() {
        return fkUser2;
    }

    public void setFkUser2(Integer fkUser2) {
        this.fkUser2 = fkUser2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipEntity that = (RelationshipEntity) o;

        if (fkActivity != null ? !fkActivity.equals(that.fkActivity) : that.fkActivity != null) return false;
        if (fkUser1 != null ? !fkUser1.equals(that.fkUser1) : that.fkUser1 != null) return false;
        if (fkUser2 != null ? !fkUser2.equals(that.fkUser2) : that.fkUser2 != null) return false;
        if (pkRelationship != null ? !pkRelationship.equals(that.pkRelationship) : that.pkRelationship != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkRelationship != null ? pkRelationship.hashCode() : 0;
        result = 31 * result + (fkActivity != null ? fkActivity.hashCode() : 0);
        result = 31 * result + (fkUser1 != null ? fkUser1.hashCode() : 0);
        result = 31 * result + (fkUser2 != null ? fkUser2.hashCode() : 0);
        return result;
    }
}
