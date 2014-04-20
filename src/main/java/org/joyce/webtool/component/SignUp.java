package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joyce.webtool.model.*;
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

    private PasswordEncoder passwordEncoder = new StandardPasswordEncoder("joyce");

    private String checkUserAuthority = "from UserEntity where username = ? ";
    private String checkUserIdAuthority = "from UserEntity where userID = ? ";

    @ResponseBody
    @RequestMapping(value = "signup/individual", method= RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody IndividualEntity individualEntity){
        Session session = sessionFactory.openSession();

        List<UserEntity> notUniqueUsername = session.createQuery(checkUserAuthority).setParameter(0, individualEntity.getUsername()).list();
        if(notUniqueUsername.size()!=0){
            return new ResponseEntity(false);
        } else{
            Integer userId = (int) (Math.random() * 10000000);
            List<UserEntity> checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
            while(checkPrimaryKey.size() != 0) {
                userId = (int) (Math.random() * 10000000);
                checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
            }


            String rawPassword = individualEntity.getPassword();
            individualEntity.setUserID(userId);
            individualEntity.setPassword(passwordEncoder.encode(rawPassword));
            individualEntity.setCardList(new ArrayList<CardEntity>());

            Transaction transaction=session.beginTransaction();
            session.save(individualEntity);
            transaction.commit();
            session.close();
            return new ResponseEntity(true);
        }
    }


    @ResponseBody
    @RequestMapping(value = "signup/company", method= RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody CompanyEntity companyEntity){
        Session session = sessionFactory.openSession();

        List<UserEntity> notUniqueUsername = session.createQuery(checkUserAuthority).setParameter(0, companyEntity.getUsername()).list();
        if(notUniqueUsername.size()!=0){
            return new ResponseEntity(false);
        } else{
            Integer userId = (int) (Math.random() * 10000000);
            List<UserEntity> checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
            while(checkPrimaryKey.size() != 0) {
                userId = (int) (Math.random() * 10000000);
                checkPrimaryKey = session.createQuery(checkUserIdAuthority).setParameter(0, userId).list();
            }


            String rawPassword = companyEntity.getPassword();
            companyEntity.setUserID(userId);
            companyEntity.setPassword(passwordEncoder.encode(rawPassword));


            Transaction transaction=session.beginTransaction();
            session.save(companyEntity);
            transaction.commit();
            session.close();
            return new ResponseEntity(true);
        }
    }
}
