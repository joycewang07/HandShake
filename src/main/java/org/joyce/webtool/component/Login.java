package org.joyce.webtool.component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;


@Controller
@RequestMapping(value = "/login")
public class Login {

    @Autowired
    private SessionFactory sessionFactory;

    private PasswordEncoder passwordEncoder;

    private String retrieveUserByUsername = "from UserEntity where username = ?";
    private final String invalidPassword = "Invalid Password";
    private final String invalidUser = "User Not Found";

    public Login() {
        passwordEncoder = new StandardPasswordEncoder("joyce");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage (HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity loginCheck(HttpServletRequest request,String username, String password, HttpServletResponse response) {

        Session session = sessionFactory.openSession();
        List<UserEntity> loginUser = session.createQuery(retrieveUserByUsername).setParameter(0, username).list();

        if (loginUser.size() == 1) {
            if (passwordEncoder.matches(password, loginUser.get(0).getPassword())) {
                request.getSession().setAttribute("user",loginUser.get(0).getUserID());
              return new ResponseEntity(true);
            } else {
                ResponseEntity responseEntity = new ResponseEntity();
                responseEntity.setSuccess(false);
                responseEntity.setMsg(invalidPassword);
                return responseEntity;
            }

        } else {
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setSuccess(false);
            responseEntity.setMsg(invalidUser);
            return responseEntity;
        }

    }

    @RequestMapping(value = "set", method = RequestMethod.GET)
    public void setRecord() {
        passwordEncoder = new StandardPasswordEncoder("joyce");

        UserEntity paul = new UserEntity();
        paul.setUsername("paul");
        paul.setPassword(passwordEncoder.encode("paul"));
        paul.setUserID(45832505);


        UserEntity natasha = new UserEntity();
        natasha.setUsername("natasha");
        natasha.setPassword(passwordEncoder.encode("natasha"));
        natasha.setUserID(31295782);

        UserEntity joyce = new UserEntity();
        joyce.setUsername("joyce");
        joyce.setPassword(passwordEncoder.encode("joyce1"));
        joyce.setUserID(51263173);

        UserEntity meaghan = new UserEntity();
        meaghan.setUsername("meaghan");
        meaghan.setPassword(passwordEncoder.encode("meaghan"));
        meaghan.setUserID(81100793);

        Session session = sessionFactory.openSession();
        session.merge(joyce);
        session.merge(paul);
        session.merge(meaghan);
        session.merge(natasha);
        session.flush();
        session.close();

    }

    @RequestMapping(value = "{user}")
    @ResponseBody
    private String testLogin(@PathVariable(value = "user") String user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "Success: " + session.getAttribute("user");
    }


    @RequestMapping(value = "file", method = RequestMethod.GET)
    @ResponseBody
    private String fileLogin(HttpServletRequest request) {
        String textFileName = "user_id.txt";
        String filePath = request.getServletContext().getRealPath("/") + "/" + textFileName;

        File file = new File(filePath);
        String user = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            user = bufferedReader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int userInt = Integer.parseInt(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", userInt);

        return "Success: " + session.getAttribute("user");
    }
}

