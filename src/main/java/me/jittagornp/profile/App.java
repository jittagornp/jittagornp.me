/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author jittagornp
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"me.jittagornp.profile"})
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
