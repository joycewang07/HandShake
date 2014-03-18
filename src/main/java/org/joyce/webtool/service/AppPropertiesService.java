package org.joyce.webtool.service;

/**
 * Created by Administrator on 14-3-17.
 */

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by zjy on 14-1-25.
 */

@Service
public class AppPropertiesService extends Properties {
    private final Logger logger =  Logger.getLogger(getClass());
    private int appLanguage = 0;

    // language
    public static final int EN = 1;
    public static final int ZH = 2;

    public AppPropertiesService() {
        try {
            this.load(new FileInputStream("src/main/webapp/WEB-INF/app.properties"));
        } catch (Exception e) {
            logger.error("Open properties file error.");
        }
    }
}
