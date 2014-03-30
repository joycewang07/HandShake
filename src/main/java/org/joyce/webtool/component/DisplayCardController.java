package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntitiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 14-3-29.
 */

@Controller
@RequestMapping(value = "/user")
public class DisplayCardController {


    private String retrieveEventList = "from  where userId in (:userIdList)";
    private String textFileName = "user_id.txt";

    @Autowired
    private SessionFactory sessionFactory;

    @ResponseBody
    @RequestMapping(value = "dispalyActivity")
    public ArrayList<ActivityEntitiy> getActivity(){

           Session session=sessionFactory.openSession();
           ArrayList<ActivityEntitiy> activityList = (ArrayList<ActivityEntitiy>) session.createSQLQuery(retrieveEventList).list();



    }

}
