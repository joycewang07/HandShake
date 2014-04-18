package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joyce.webtool.model.CardEntity;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 14-4-13.
 */

@Controller
@RequestMapping()
public class SignUp {
    @Autowired
    private SessionFactory sessionFactory;

    private PasswordEncoder passwordEncoder;

    private String checkUserAuthority = "from UserEntity where username = ? ";
    private String checkUserIdAuthority = "from UserEntity where userID = ? ";

//    @ResponseBody
//    @RequestMapping(value = "signup", method= RequestMethod.POST)
//    public ResponseEntity signUp(@RequestBody UserEntity userEntity){
//        Session session = sessionFactory.openSession();
//
//        List<UserEntity> notUniqueUsername = session.createQuery(checkUserAuthority).setParameter(0, userEntity.getUsername()).list();
//        if(notUniqueUsername.size()!=0){
//            return new ResponseEntity(false);
//        } else{
//            Integer userId = (int) (Math.random() * 10000000);
//            List<UserEntity> checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
//            while(checkPrimaryKey.size() != 0) { // TODO !!!!
//                userId = (int) (Math.random() * 10000000);
//                checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
//            }
//
//            passwordEncoder = new StandardPasswordEncoder("joyce");
//            String rawPassword = userEntity.getPassword();
//            userEntity.setUserID(userId);
//            userEntity.setPassword(passwordEncoder.encode(rawPassword));
//            userEntity.setCardList(new ArrayList<CardEntity>());
//
//            Transaction transaction=session.beginTransaction();
//            session.save(userEntity);
//            transaction.commit();
//            session.close();
//            return new ResponseEntity(true);
//        }
//    }
}
