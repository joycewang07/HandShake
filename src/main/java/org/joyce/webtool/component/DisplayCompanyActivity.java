package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntity;
import org.joyce.webtool.model.CompanyEntity;
import org.joyce.webtool.model.RelationshipEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping(value = "/company/activity", method = RequestMethod.GET)
public class DisplayCompanyActivity {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    private String retrieveCompanyByUserId = "from UserEntity where userID = ? ";

    public DisplayCompanyActivity() {
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<ActivityEntity> getActivityList(HttpServletRequest request) {

        int userId = accountService.getCurrentUser(request);
        Session session = sessionFactory.openSession();
        List<CompanyEntity> companyEntityList = session.createQuery(retrieveCompanyByUserId).setParameter(0, userId).list();

        CompanyEntity companyEntity = companyEntityList.get(0);

        List<ActivityEntity> activityEntityList = companyEntity.getActivityEntityList();

        return activityEntityList;
    }



}
