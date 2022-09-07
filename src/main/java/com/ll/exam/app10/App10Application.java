package com.ll.exam.app10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing // BaseEntity 의 @EntityListeners 사용하기 위해 필요
public class App10Application {
	public static void main(String[] args) {
		SpringApplication.run(App10Application.class, args);
	}

}
