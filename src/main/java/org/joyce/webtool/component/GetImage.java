package org.joyce.webtool.component;

import org.hibernate.SessionFactory;
import org.joyce.webtool.model.Html;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Administrator on 14-4-2.
 */

@Controller
@RequestMapping(value = "/getImages", method = RequestMethod.GET)
public class GetImage {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;


   // @RequestMapping(value = "{imageId}")
    @ResponseBody
    private ArrayList<Html> getImages(){
//        if(accountService.getCurrentUser(request)==null){
//            //Warn Login
//        }
        ArrayList<Html> imageList= new ArrayList<>();
        Html image= new Html();
        image.setContent("<span><img src=\"/images/football.jpg\" alt=\"Error\"></span>");

        imageList.add(image);


        return imageList;
    }
}
