package org.joyce.webtool.component;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

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

    @RequestMapping(value = "file", method = RequestMethod.GET)
    @ResponseBody
    private String fileLogin(HttpServletRequest request){
       String textFileName = "user_id.txt";
       String filePath= request.getServletContext().getRealPath("/") + "/" + textFileName;

        File file= new File(filePath);
        String user=null;
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));

            user= bufferedReader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int userInt= Integer.parseInt(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", userInt);

        return  "Success: " + session.getAttribute("user");
    }
}

