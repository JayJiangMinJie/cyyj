package com.geovis.cyyj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Jay
 * @since 2022-09-23
 */
@EnableTransactionManagement
@SpringBootApplication
public class GeovisStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeovisStartApplication.class, args);
    }
}
