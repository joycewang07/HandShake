package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.model.UserEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 14-4-6.
 */

@Controller
@RequestMapping(value="/mycard" )
public class ManageMyCard {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;



    private String retrieveCurrentUserByUserId = " from UserEntity where userID = ? ";

   //when hit on my card, business card info should be loaded from database
    @ResponseBody
    @RequestMapping(value="display", method= RequestMethod.GET)
    private UserEntity displayMyCard(HttpServletRequest request){
        Integer userId= accountService.getCurrentUser(request);
        if(userId==null){
           new ResponseEntity().setSuccess(false);
        }
        Session session= sessionFactory.openSession();
        UserEntity user= (UserEntity)session.get(UserEntity.class, userId);
                return user;
    }


    @RequestMapping(value="update", method= RequestMethod.POST)
    private void updateMyCard(@RequestBody UserEntity userEntity, HttpServletRequest request){
        Integer userId= accountService.getCurrentUser(request);
        if(userId==null){
            //Warning Login
        }

        Session session= sessionFactory.openSession();
        session.update(userEntity);


    }


}
