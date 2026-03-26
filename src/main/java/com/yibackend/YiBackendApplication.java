package com.yibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class YiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiBackendApplication.class, args);
    }

}
