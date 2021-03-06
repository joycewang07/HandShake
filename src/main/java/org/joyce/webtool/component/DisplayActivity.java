package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntity;
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
@RequestMapping(value = "/activity", method = RequestMethod.GET)
public class DisplayActivity {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    private String retrieveRelationshipByUserId = "from RelationshipEntity where fkUser1 = ? ";

    public DisplayActivity() {
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<ActivityEntity> getActivityList(HttpServletRequest request) {

        int userId = accountService.getCurrentUser(request);
        Session session = sessionFactory.openSession();
        List<RelationshipEntity> relationshipEntityList = session.createQuery(retrieveRelationshipByUserId).setParameter(0, userId).list();

        ArrayList<ActivityEntity> activityEntityList = new ArrayList<>();
        for (RelationshipEntity relationshipEntity : relationshipEntityList) {
            ActivityEntity activityEntity = relationshipEntity.getFkActivity();
            if (!activityEntityList.contains(activityEntity)) {
                activityEntityList.add(activityEntity);
            }
        }
        // ArrayList<ActivityEntity> activityEntityList= (ArrayList<ActivityEntity>)session.createQuery(retrieveRelationshipByUserId).setParameter(0, userId).list();

        return activityEntityList;
    }



}
