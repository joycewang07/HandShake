package org.joyce.webtool.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by junyan Zhang on 14-3-30.
 */

@Controller

public class MainPage {

    @RequestMapping (value="/", method = RequestMethod.GET)
    public String mainPage () {
        return "handshake.jsp";
    }

    @RequestMapping (value="/company", method = RequestMethod.GET)
    public String tomainPage () {
        return "company.jsp";
    }

    @RequestMapping (value="/admin", method = RequestMethod.GET)
    public String adminMainPage () {
        return "admin.jsp";
    }
}
