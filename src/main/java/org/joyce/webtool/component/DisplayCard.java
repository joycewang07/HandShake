package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 14-3-29.
 */

@Controller
@RequestMapping(value = "/user") // /user/dis
public class DisplayCard {


    private String retrieveEventList = "from  where userId in (:userIdList)";
    private String textFileName = "user_id.txt";

    @Autowired
    private SessionFactory sessionFactory;

    @ResponseBody
    @RequestMapping(value = "displayActivity")
    public ArrayList<ActivityEntity> getActivity(){

           Session session=sessionFactory.openSession();
           ArrayList<ActivityEntity> activityList = (ArrayList<ActivityEntity>) session.createSQLQuery(retrieveEventList).list();

        return null;

    }

}
