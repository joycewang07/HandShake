package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 14-4-20.
 */

@Controller
@RequestMapping(value = "/admin")
public class DisplayAdminGeneralInfo {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    String retrieveGeneralInfo = "select company.company_name as companyName, activity.activity_name as activityName, count(distinct relationship.fk_user2) as userAmount " +
            "from activity join company on activity.fk_company = company.pk_user" +
            "  join relationship on activity.pk_activity = relationship.fk_activity" +
            "  group by activity.pk_activity;";


    @ResponseBody
    @RequestMapping(value = "displayGeneralInfo", method = RequestMethod.GET)
    public List<Map<String,Object>> displayGeneralInfo(HttpServletRequest request){

        Integer currentUser = accountService.getCurrentUser(request);
        if(currentUser==null){
          HashMap<String, Object> resultMap =  new HashMap<String, Object>();
            resultMap.put("success", "false");
            LinkedList<Map<String, Object>> resultList = new LinkedList<>();
            resultList.add(resultMap);
            return resultList;
        }
        else{

            Session session = sessionFactory.openSession();

            List<Object[]>  generalInfo = session.createSQLQuery(retrieveGeneralInfo).list();
            LinkedList<Map<String, Object>> resultMapList = new LinkedList<>();
            for(Object[] objects: generalInfo){
                TreeMap<String, Object> map = new TreeMap<>();
                map.put("companyName", objects[0]);
                map.put("activityName",objects[1]);
                map.put("userAmount",  objects[2]);
                resultMapList.add(map);
            }
            session.close();
            return  resultMapList;
        }

    }
}
