package org.joyce.webtool.model;

import javax.persistence.Entity;

/**
 * Created by Administrator on 14-4-8.
 */


public class ResponseEntity {
    private boolean success;
    private String msg;

    public ResponseEntity(boolean success) {
        this.success= success;
    }

    public ResponseEntity() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
