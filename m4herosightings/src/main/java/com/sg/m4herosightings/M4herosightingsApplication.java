package com.sg.m4herosightings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class M4herosightingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(M4herosightingsApplication.class, args);
    }

}
