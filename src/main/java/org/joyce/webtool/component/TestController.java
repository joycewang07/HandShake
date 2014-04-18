package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.CompanyEntity;
import org.joyce.webtool.model.IndividualEntity;
import org.joyce.webtool.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by junyan Zhang on 14-4-17.
 */

@Controller
@RequestMapping(value = "/test", method = RequestMethod.GET)
public class TestController {
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping
    public void test () {
        Session session = sessionFactory.openSession();
        IndividualEntity individualEntity = (IndividualEntity)session.get(IndividualEntity.class, 31295782);
        CompanyEntity companyEntity = (CompanyEntity)session.get(CompanyEntity.class, 1);
        UserEntity userEntity = (UserEntity)session.get(UserEntity.class, 31295782);

        session.close();
    }
}
