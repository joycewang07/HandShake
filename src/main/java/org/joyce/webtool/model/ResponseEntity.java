package org.joyce.webtool.model;

import javax.persistence.Entity;

/**
 * Created by Administrator on 14-4-8.
 */


public class ResponseEntity {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
