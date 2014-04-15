package org.joyce.webtool.component;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.ActivityEntity;
import org.joyce.webtool.model.CardEntity;
import org.joyce.webtool.model.RelationshipEntity;
import org.joyce.webtool.model.UserEntity;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-3-29.
 */

@Controller
@RequestMapping(value = "/cardList")
public class DisplayCard {

    private String retrieveUserListByCurUserId = " from RelationshipEntity where fkUser1 = ? and fkActivity.activityId = ? ";
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<CardEntity> getCardList(int activityId, HttpServletRequest request) {
        int currentUserId = accountService.getCurrentUser(request);
        if (currentUserId == 0) {
            //Plz login to use service
        }
        Session session = sessionFactory.openSession();

        List<RelationshipEntity> relationshipEntityList = session.createQuery(retrieveUserListByCurUserId).setParameter(0, currentUserId).setParameter(1, activityId).list();
        ArrayList<CardEntity> cardList = new ArrayList<>();

        for (RelationshipEntity relationshipEntity : relationshipEntityList) {
            UserEntity userEntity = relationshipEntity.getFkUser2();
            for (CardEntity cardEntity : userEntity.getCardList()) {
                cardEntity.generateHtml();
                cardList.add(cardEntity);
            }


        }

        return cardList;
    }


}
