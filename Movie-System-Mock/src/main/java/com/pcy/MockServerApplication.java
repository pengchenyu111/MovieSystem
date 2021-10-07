package com.pcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author PengChenyu
 * @since 2021-10-06 13:32:23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockServerApplication.class, args);
    }
}
