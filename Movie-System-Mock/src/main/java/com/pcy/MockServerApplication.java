package com.pcy;

import com.pcy.service.MovieDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author PengChenyu
 * @since 2021-10-06 13:32:23
 */
@SpringBootApplication
public class MockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockServerApplication.class, args);
    }
}
