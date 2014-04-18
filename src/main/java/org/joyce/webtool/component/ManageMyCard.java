package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.CardEntity;
import org.joyce.webtool.model.IndividualEntity;
import org.joyce.webtool.model.ResponseEntity;
import org.joyce.webtool.model.UserEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 14-4-6.
 */

@Controller
@RequestMapping(value = "/mycard")
public class ManageMyCard {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;

    private String retrieveUserByUserId = "from UserEntity where userID = ? ";

    //when hit on my card, business card info should be loaded from database
    @ResponseBody
    @RequestMapping(value = "display", method = RequestMethod.GET)
    private CardEntity displayMyCard(HttpServletRequest request) {
        Integer userId = accountService.getCurrentUser(request);
        if (userId == null) {
            //new ResponseEntity().setSuccess(false);
        }
        Session session = sessionFactory.openSession();
        //get only get from primary key
        IndividualEntity individualEntity = (IndividualEntity) session.get(IndividualEntity.class, userId);
        CardEntity card = individualEntity.getCardList().get(0);

        return card;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    private ResponseEntity updateMyCard(@RequestBody CardEntity cardEntity, HttpServletRequest request) {
        Integer userId = accountService.getCurrentUser(request);
        if (userId == null) {
         return new ResponseEntity(false);
        } else {

            Session session = sessionFactory.openSession();
            IndividualEntity individualEntity = (IndividualEntity) session.get(IndividualEntity.class, userId);
            cardEntity.setIndividualEntity(individualEntity);
            session.merge(cardEntity);

            session.flush();
            session.close();
            return new ResponseEntity(true);
        }

    }


}
