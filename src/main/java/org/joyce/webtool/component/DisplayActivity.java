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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by junyan Zhang on 14-3-30.
 */

@Controller
@RequestMapping(value="/activity", method = RequestMethod.GET)
public class DisplayActivity {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    private String retrieveRelationshipByUserId = "from RelationshipEntity where fkUser1 = ? ";
    private String retrieveActivityEntityById = " from ActivityEntity where activityId in (:activityIdList)";
    public DisplayActivity() {
    }

    @ResponseBody
    @RequestMapping(value="list", method = RequestMethod.GET)
    public List<ActivityEntity> getActivityList (HttpServletRequest request) {

        int userId = accountService.getCurrentUser(request);
        Session session = sessionFactory.openSession();
        List<RelationshipEntity> relationshipEntityList = session.createQuery(retrieveRelationshipByUserId).setParameter(0, userId).list();

        List<Integer> activityIdList = new LinkedList<>();

        for(RelationshipEntity relationshipEntity: relationshipEntityList){
            activityIdList.add(relationshipEntity.getFkActivity());
        }

        List<ActivityEntity> activityEntityList= session.createQuery(retrieveActivityEntityById).setParameterList("activityIdList", activityIdList).list();

        //session.createQuery(retrieveActivityEntityById).setp
        return activityEntityList;
    }


}
