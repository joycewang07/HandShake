package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 14-4-21.
 */
@Controller
public class GeneratePdf {



        @RequestMapping(value = "/report", method = RequestMethod.GET)
        public ModelAndView doProcess(@RequestBody List<Map<String,Object>> pdf,Locale locale, ModelMap model) {

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
            String formattedDate = dateFormat.format(date);
            model.addAttribute("time", formattedDate );


            return new ModelAndView(new MyView(), model);
        }
    }



