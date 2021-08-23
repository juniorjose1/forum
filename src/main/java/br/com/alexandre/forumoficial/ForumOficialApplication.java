package br.com.alexandre.forumoficial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ForumOficialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumOficialApplication.class, args);
	}

}
