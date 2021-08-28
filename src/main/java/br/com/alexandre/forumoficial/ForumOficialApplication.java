package br.com.alexandre.forumoficial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ForumOficialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumOficialApplication.class, args);
	}

}
