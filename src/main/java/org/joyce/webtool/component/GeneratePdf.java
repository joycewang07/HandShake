package org.joyce.webtool.component;

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
       List<ModelMap> result = createModelMap();

        @RequestMapping(value = "/report", method = RequestMethod.POST)
        public ModelAndView doProcess( Locale locale,@RequestBody List<ModelMap> result) {
            //List<Map<String,Object>> pdf,


            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
            String formattedDate = dateFormat.format(date);
            //model.addAttribute("time", formattedDate );


            return new ModelAndView(new MyView(), new ModelMap());
        }

        public List<ModelMap> createModelMap(){

            ModelMap modelMap = new ModelMap();
            //modelMap.addAllAttributes(,null);
            modelMap.put("companyName",null);
            modelMap.put("activityName",null);
            modelMap.put("userAmount", null);
            List<ModelMap> result = new LinkedList<>();
            result.add(modelMap);
                   return  result;
        }
    }



