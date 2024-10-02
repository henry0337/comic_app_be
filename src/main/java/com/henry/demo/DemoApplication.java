package com.henry.demo;

import com.henry.demo.utils.Constant;
import io.sentry.spring.jakarta.EnableSentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSentry(dsn = Constant.DSN)
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
