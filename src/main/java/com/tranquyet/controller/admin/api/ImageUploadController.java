package com.tranquyet.controller.admin.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tranquyet.domain.UploadFileResponse;
import com.tranquyet.service.impl.StorageService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController(value = "image-upload")
@RequestMapping("/api/image")
public class ImageUploadController {

	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

	@Autowired
	private StorageService storageService;

	@PostMapping(value = "/upload")
	public ResponseEntity<UploadFileResponse> uploadImage(@RequestParam("file") MultipartFile file) {
		if (file == null) {
			logger.warn("File is null");
		}
		String fileName = storageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/image/download/")
				.path(fileName).toUriString();

		UploadFileResponse domain = new UploadFileResponse();
		domain.setFileDownloadUri(fileDownloadUri);
		logger.info("----> domain: " + domain.toString());
		return new ResponseEntity<>(domain, HttpStatus.ACCEPTED);
	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable(name = "fileName") String fileName,
			HttpServletRequest request) {
		Resource resource = storageService.loadFileAsResource(fileName);
		String contentType = null;
		logger.info("----> resource: " + resource.toString());
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			logger.info("---> ContentType: " + contentType.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (contentType == null) {
			contentType = "application/oct-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
