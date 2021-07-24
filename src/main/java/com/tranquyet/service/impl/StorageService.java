package com.tranquyet.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tranquyet.property.AddressProperties;

@Service(value = "storage-service")
public class StorageService {

	private final Logger log = LoggerFactory.getLogger(StorageService.class);

	private final Path fileStorageLocation;

	/**
	 * 
	 * @param addressProperties
	 * 
	 * @effects: create path from @ConfigurationProperties(prefix="file")
	 */
	@Autowired
	public StorageService(AddressProperties addressProperties) {
		this.fileStorageLocation = Paths.get(addressProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (IOException e) {
			log.info(e.toString());
		}
	}

	// create
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if (fileName.contains("..")) {
			log.info("Sorry! filename contain special characters..");
		}
		// copy file to the target location (Replacing existing file with the same name)
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		log.info("---> TargetLocation: "+targetLocation.toString());
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			log.info("---> fileName: " + fileName);
			return fileName;
		} catch (IOException e) {
			log.info(e.toString());
		}
		return fileName;
	}

	public Resource loadFileAsResource(String fileName) {
		Path filePath = this.fileStorageLocation.resolve(fileName);

		try {
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				log.info("---> resources: " + resource.getDescription());
				return resource;
			} else {
				log.info("StorageService not found rosource!");
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return null;
	}

}
