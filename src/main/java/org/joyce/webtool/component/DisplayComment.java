package org.joyce.webtool.component;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.CommentEntity;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 14-4-9.
 */
@Controller
@RequestMapping(value = "/comment")
public class DisplayComment {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    private String retrieveComment = "from CommentEntity where user1Id = ? and user2Id = ?";


    @ResponseBody
    @RequestMapping(value="display",method= RequestMethod.GET)
    public CommentEntity getComment(int user2Id, HttpServletRequest request){

        Integer currentId = accountService.getCurrentUser(request);

        if(currentId==null){

        }

        Session session=sessionFactory.openSession();
        List<CommentEntity> comment = session.createQuery(retrieveComment).setParameter(0,currentId).setParameter(1,user2Id).list();
        if(comment.size()==0){
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setComment(null);
            commentEntity.setUser1Id(currentId);
            commentEntity.setUser2Id(user2Id);
            commentEntity.setSuccess(true);
            return commentEntity;
        }
        else{
            comment.get(0).setSuccess(true);
            return comment.get(0);
        }

    }

    @ResponseBody
    @RequestMapping(value="update",method= RequestMethod.POST)
    public ResponseEntity updateComment(@RequestBody CommentEntity commentEntity, HttpServletRequest request){

        int user1Id = accountService.getCurrentUser(request);

        int user2Id = commentEntity.getUser2Id();

        Session session=sessionFactory.openSession();
        List<CommentEntity> comment = session.createQuery(retrieveComment).setParameter(0,user1Id).setParameter(1,user2Id).list();

        if(comment.size()==0){
            session.save(commentEntity);
        } else {
            CommentEntity updateComment = comment.get(0);
            updateComment.setComment(commentEntity.getComment());
            session.update(updateComment);
        }
        session.flush();
        session.close();

        return new ResponseEntity(true);
    }
}
