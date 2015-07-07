package org.drmod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class BootNameApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootNameApplication.class, args);
    }
}
