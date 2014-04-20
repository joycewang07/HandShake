package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joyce.webtool.model.*;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-4-19.
 */

@Controller
@RequestMapping(value = "company")
public class CreateActivity {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

  //  private String checkActivityId = "from ActivityEntity where activityId = ? ";
  //  private String checkUserIdAuthority = "from UserEntity where userID = ? ";
   // private String retrieveActivityNum = "select count(*) from ActivityEntity";
   // private PasswordEncoder passwordEncoder = new StandardPasswordEncoder("joyce");
  @ResponseBody
  @RequestMapping(value = "createActivity", method= RequestMethod.POST)
    public ResponseEntity createActivity(@RequestBody ActivityEntity activityEntity,HttpServletRequest request){

        Session session = sessionFactory.openSession();

        CompanyEntity companyEntity = (CompanyEntity) session.get(CompanyEntity.class, accountService.getCurrentUser(request));

            Integer activityId = (int) (Math.random() * 100);
            activityEntity.setActivityId(activityId);
            companyEntity.addActivity(activityEntity);

            Transaction transaction=session.beginTransaction();
            session.save(activityEntity);
            transaction.commit();
            session.close();
            return new ResponseEntity(true);

    }


}
