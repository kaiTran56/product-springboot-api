package com.tranquyet.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "file")
@Setter
@Getter
public class FileStorageProperties {
	private String uploadDir;

}