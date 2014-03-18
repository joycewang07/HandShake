package org.joyce.webtool.component;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class HelloController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(Model model) {
        String date = (new Date()).toString();
        model.addAttribute("date", date);
        return "home";
    }
    
}
