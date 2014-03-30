package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    private String retrieveActivityByUserId = "from o ";

    public DisplayActivity() {
    }



    @RequestMapping(value="list", method = RequestMethod.GET)
    public List<ActivityEntity> getActivityList (HttpServletRequest request) {
        String user = accountService.getCurrentUser(request);
        if (user != null) {
            Session session = sessionFactory.openSession();
            List<ActivityEntity> activityEntityList = session.createQuery(retrieveActivityByUserId).list()
        }
    }

}
