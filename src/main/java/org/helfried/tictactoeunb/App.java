package org.helfried.tictactoeunb;

import org.helfried.tictactoeunb.configs.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class,args);
    }

}
