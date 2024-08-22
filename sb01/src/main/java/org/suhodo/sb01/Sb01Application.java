package org.suhodo.sb01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// jpa변화 이벤트 활성화
@EnableJpaAuditing
@SpringBootApplication
public class Sb01Application {

	public static void main(String[] args) {
		SpringApplication.run(Sb01Application.class, args);
	}

}