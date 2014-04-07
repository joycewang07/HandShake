package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.UserEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value="update", method= RequestMethod.POST)
    private void updateMyCard(UserEntity userEntity, HttpServletRequest request){
       Integer userId= accountService.getCurrentUser(request);
        if(userId==null){
            //Warning Login
        }

        Session session= sessionFactory.openSession();
        session.update(userEntity);

    }

}
