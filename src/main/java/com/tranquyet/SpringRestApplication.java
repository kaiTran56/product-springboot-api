package com.tranquyet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tranquyet.property.FileStorageProperties;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);

	}

}
