package org.joyce.webtool.component;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by junyan Zhang on 14-3-30.
 */

@Controller
public class DisplayActivity {
    @Autowired
    private SessionFactory sessionFactory;

    public DisplayActivity() {
    }



}
