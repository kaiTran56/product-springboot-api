package com.tranquyet.controller.admin.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tranquyet.domain.UploadFileResponse;
import com.tranquyet.dto.NewsDTO;
import com.tranquyet.service.NewsService;
import com.tranquyet.service.impl.StorageService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController(value = "news")
@RequestMapping("/api/news")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private StorageService storageService;

	@GetMapping
	public ResponseEntity<NewsDTO> getList(@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "limit", required = false) Optional<Integer> limit) {
		PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, limit.orElse(2));
		List<NewsDTO> listDTO = newsService.findAll(pageRequest);
		NewsDTO dto = new NewsDTO();
		dto.setListResult(listDTO);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NewsDTO> getNews(@PathVariable("id") long id){
		
		logger.info("-------> GET ID NEWS: "+id);
		NewsDTO newsDTO = new NewsDTO();
		
		newsDTO = newsService.getNewsById(id);
		
		return new ResponseEntity<>(newsDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<NewsDTO> save(@RequestBody NewsDTO dto) {
		newsService.save(dto);
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/thumbnail")
	public ResponseEntity<UploadFileResponse> uploadThumbnail(@RequestParam("file") MultipartFile file) {
		logger.info(file.getContentType());
		if (!file.getContentType().contains("image")) {
			logger.info("----> Not a image!");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		String fileName = storageService.storeFile(file);
		String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/news/download/").path(fileName)
				.toUriString();

		UploadFileResponse domain = new UploadFileResponse();
		domain.setFileDownloadUri(uri);
		return new ResponseEntity<>(domain, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/edit")
	public ResponseEntity<> gi

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

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		long[] ids = { id };
		newsService.remove(ids);
		logger.info("----> Delete Successfully!");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
