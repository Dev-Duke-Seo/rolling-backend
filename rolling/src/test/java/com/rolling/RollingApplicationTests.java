package com.rolling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RollingApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // 스프링 컨텍스트가 정상적으로 로드되는지 확인
        assertNotNull(applicationContext);
    }

    @Test
    void applicationNameShouldBeCorrect() {
        // 애플리케이션 이름이 설정 파일과 일치하는지 확인
        String appName = applicationContext.getEnvironment().getProperty("spring.application.name");
        assertNotNull(appName);
        assert (appName.equals("rolling"));
    }
}