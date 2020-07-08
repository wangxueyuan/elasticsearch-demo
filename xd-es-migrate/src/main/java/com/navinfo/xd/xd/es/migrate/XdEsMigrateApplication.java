package com.navinfo.xd.xd.es.migrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class XdEsMigrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdEsMigrateApplication.class, args);
    }

}
