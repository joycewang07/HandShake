package org.joyce.webtool.component;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;



@Controller
@RequestMapping(value = "/login")
public class Login {

    @Autowired
    private SessionFactory sessionFactory;

    private String retrieveUserByUsername= "from UserEntity where username = ? and password = ? ";



    @RequestMapping(value = "{username}/{password}", method= RequestMethod.POST)

    private ModelAndView loginCheck(@PathVariable(value="username") String username,@PathVariable(value="passwrod")String password,HttpServletRequest request){

        Session session = sessionFactory.openSession();
        List<UserEntity> loginUser =  session.createQuery(retrieveUserByUsername).setParameter(0,username).setParameter(1,password).list();
        if(loginUser.size()==1){
            HttpSession requestSession = request.getSession();
            requestSession.setAttribute("user", loginUser.get(0));
            return new ModelAndView("handshake.jsp");
        }
        else
            return null;
    }



    @RequestMapping(value = "{user}")
    @ResponseBody
    private String testLogin (@PathVariable(value="user") String user, HttpServletRequest request) {
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

