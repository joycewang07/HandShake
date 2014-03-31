package org.joyce.webtool.component;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by junyan Zhang on 14-3-30.
 */

@Controller
@RequestMapping(value = "/login")
public class Login {

    @RequestMapping(value = "{user}")
    @ResponseBody
    private String testLogin (@PathVariable(value="user") int user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "Success: " + session.getAttribute("user");
    }
}

