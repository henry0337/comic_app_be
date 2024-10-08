package com.henry.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile({"dev", "test"})
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {}

}
