package com.staj.acg.photoz.clone.web;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.staj.acg.photoz.clone.model.Photo;
import com.staj.acg.photoz.clone.service.PhotozService;

@RestController
public class PhotozController {

	private final PhotozService photozService;

	public PhotozController(PhotozService photozService) {
		this.photozService = photozService;
	}

	// @GetMapping("/")
	// public String hello() {
	// return "Hello Mom.";
	// }

	@GetMapping("/photoz")
	public Iterable<Photo> get() {
		return photozService.get();
	}

	@GetMapping("/photoz/{id}")
	public Photo getPhotoById(@PathVariable Integer id) {
		Photo photo = photozService.get(id);
		if (photo == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return photo;
	}

	@DeleteMapping("/photoz/{id}")
	public void deleteById(@PathVariable Integer id) {
		photozService.remove(id);
	}

	@PostMapping("/photoz")
	public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException {
		return photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
	}
}
