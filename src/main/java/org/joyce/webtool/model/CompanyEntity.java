package org.joyce.webtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by junyan Zhang on 14-4-17.
 */
@Entity
@Table(name = "company", schema = "", catalog = "")
public class CompanyEntity extends UserEntity {
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;

    @JsonIgnore
    @OneToMany(mappedBy = "companyEntity", fetch = FetchType.EAGER, cascade = {CascadeType.ALL} )
    private List<ActivityEntity> activityEntityList = new LinkedList<>();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<ActivityEntity> getActivityEntityList() {
        return activityEntityList;
    }

    public void setActivityEntityList(List<ActivityEntity> activityEntityList) {
        this.activityEntityList = activityEntityList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
