package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Joyce on 14-4-21.
 */

@Controller
public class Error {
    @RequestMapping(value = "/securityCheckError" , method = RequestMethod.GET)
    public String securityCheckError () {
        return "securityCheckError.jsp";
    }
}
