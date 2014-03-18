package org.joyce.webtool.config;

/**
 * Created by Administrator on 14-3-17.
 */
import com.mchange.v2.c3p0.DataSources;
import org.hibernate.SessionFactory;
import org.joyce.webtool.service.AppPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by zjy on 14-1-13.
 */

@Configuration
public class PersistenceConfig {
    @Autowired
    private AppPropertiesService appPropertiesService;

    @Bean
    public SessionFactory createSessionFactory () {
        // make sure driver class exists
        String driverClass = appPropertiesService.getProperty("driverClass");
        if(driverClass != null) {
            try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        DataSource pooledDataSource = null;
        try {
            DataSource unpooledDataSource = DataSources.unpooledDataSource(
                    appPropertiesService.getProperty("jdbcUrl"),
                    appPropertiesService.getProperty("userName"),
                    appPropertiesService.getProperty("password"));
            pooledDataSource = DataSources.pooledDataSource(unpooledDataSource, appPropertiesService);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(pooledDataSource);
        // some hibernate configurations
        sessionFactoryBuilder.setProperty("hibernate.dialect", appPropertiesService.getProperty("hibernate.dialect"));
        sessionFactoryBuilder.setProperty("hibernate.show_sql", appPropertiesService.getProperty("hibernate.show_sql"));
        sessionFactoryBuilder.setProperty("hibernate.format_sql", appPropertiesService.getProperty("hibernate.format_sql"));
        sessionFactoryBuilder.setProperty("hibernate.use_sql_comments", appPropertiesService.getProperty("hibernate.use_sql_comments"));

        // scan all entity classes
        sessionFactoryBuilder.scanPackages("org.joyce.webtool.model");

        return sessionFactoryBuilder.buildSessionFactory();
    }

}
