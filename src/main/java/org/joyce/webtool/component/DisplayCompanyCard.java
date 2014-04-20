package org.joyce.webtool.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joyce.webtool.model.*;
import org.joyce.webtool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-4-20.
 */

@Controller
@RequestMapping(value = "/company/cardList")
public class DisplayCompanyCard {

    private String retrieveActivityById = " from RelationshipEntity where fkActivity.activityId = ? ";
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountService accountService;


    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<CardEntity> getCardList(int activityId, HttpServletRequest request) {

        int currentUserId = accountService.getCurrentUser(request);
        if (currentUserId == 0) {
            //Plz login to use service
        }
        Session session = sessionFactory.openSession();

        List<RelationshipEntity> relationshipList  = session.createQuery(retrieveActivityById).setParameter(0, activityId).list();

        ArrayList<CardEntity> cardList = new ArrayList<>();
        ArrayList<IndividualEntity> individualEntityList = new ArrayList<>();
    //people can meet multiple people during one event, only add people that are new to the individual list
        for (RelationshipEntity relationshipEntity : relationshipList) {
               if(!individualEntityList.contains(relationshipEntity.getFkUser2())){
                   individualEntityList.add(relationshipEntity.getFkUser2());
               }
        }
        for(IndividualEntity individualEntity: individualEntityList){
            for(CardEntity cardEntity: individualEntity.getCardList()){
                cardEntity.generateHtml();
                cardList.add(cardEntity);
            }}


        return cardList;
    }


}
