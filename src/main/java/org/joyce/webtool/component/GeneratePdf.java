package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.*;

/**
 * Created by Administrator on 14-4-21.
 */
@Controller
public class GeneratePdf {

    @Autowired
    private SessionFactory sessionFactory;

    String retrieveGeneralInfo = "select company.company_name as companyName, activity.activity_name as activityName, count(distinct relationship.fk_user2) as userAmount " +
            "from activity join company on activity.fk_company = company.pk_user" +
            "  join relationship on activity.pk_activity = relationship.fk_activity" +
            "  group by activity.pk_activity;";


    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView doProcess(ModelMap model) {

//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//        String formattedDate = dateFormat.format(date);

        Session session = sessionFactory.openSession();

        List<Object[]> generalInfo = session.createSQLQuery(retrieveGeneralInfo).list();

        session.close();

        model.addAttribute("pdfData",generalInfo);


        return new ModelAndView(new MyView(), model);
    }
}
