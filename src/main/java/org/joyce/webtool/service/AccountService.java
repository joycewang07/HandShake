package org.joyce.webtool.service;

import org.hibernate.SessionFactory;
import org.joyce.webtool.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by junyan Zhang on 14-3-30.
 */

@Service
public class AccountService {
    @Autowired
    private  SessionFactory sessionFactory;

    public AccountService() {
    }

    public boolean loginValidate (String userName, String password) {

        return true;
    }

    public int getCurrentUser (HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (int)session.getAttribute("user");
    }


}
