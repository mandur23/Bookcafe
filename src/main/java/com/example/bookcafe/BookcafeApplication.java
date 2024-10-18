// 이 클래스는 Spring Boot 애플리케이션의 진입점입니다.
// 애플리케이션을 시작하고 설정을 자동으로 구성합니다.

package com.example.bookcafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookcafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookcafeApplication.class, args);
      }

}