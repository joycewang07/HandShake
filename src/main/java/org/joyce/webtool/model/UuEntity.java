package org.joyce.webtool.model;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-17.
 */
@Entity
@Table(name = "uu", schema = "", catalog = "")
public class UuEntity {
    private int column1;
    private int column2;
    private int column3;

    @Id
    @Basic
    @Column(name = "column_1")
    public int getColumn1() {
        return column1;
    }

    public void setColumn1(int column1) {
        this.column1 = column1;
    }

    @Basic
    @Column(name = "column_2")
    public int getColumn2() {
        return column2;
    }

    public void setColumn2(int column2) {
        this.column2 = column2;
    }

    @Basic
    @Column(name = "column_3")
    public int getColumn3() {
        return column3;
    }

    public void setColumn3(int column3) {
        this.column3 = column3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UuEntity uuEntity = (UuEntity) o;

        if (column1 != uuEntity.column1) return false;
        if (column2 != uuEntity.column2) return false;
        if (column3 != uuEntity.column3) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = column1;
        result = 31 * result + column2;
        result = 31 * result + column3;
        return result;
    }
}
