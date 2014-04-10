package org.joyce.webtool.model;

import javax.persistence.*;
import java.awt.print.PrinterGraphics;

/**
 * Created by Administrator on 14-4-9.
 */

@Entity
@Table(name = "comment", schema = "", catalog = "")
public class CommentEntity {
    @Id
    @Column(name="pk_comment")
    private Integer pkComment;

    @Column(name="fk_user1")
    private Integer user1Id;

    @Column(name="user2")
    private Integer user2Id;

    @Column(name="comment")
    private String comment;

    @Transient
    private boolean success;

    public CommentEntity() {
    }

    public Integer getPkComment() {
        return pkComment;
    }

    public void setPkComment(Integer pkComment) {
        this.pkComment = pkComment;
    }

    public Integer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public Integer getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
