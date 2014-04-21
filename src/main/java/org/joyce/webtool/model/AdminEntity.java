package org.joyce.webtool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 14-4-20.
 */

@Entity
@Table(name = "admin", schema = "", catalog = "")
public class AdminEntity extends UserEntity{

    @Column(name = "admin_name")
    private String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
