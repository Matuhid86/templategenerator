package com.api.readdatabase.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.readdatabase.dtos.BaseDto;
import com.api.readdatabase.entities.BaseEntity;
import com.api.readdatabase.services.BaseService;

public interface BaseCrudController<D extends BaseDto, E extends BaseEntity> {

	public abstract BaseService<D, E> getService();

	@GetMapping(value = "/{id}")
	default public ResponseEntity<?> getOne(@PathVariable String id) throws Exception {

		if (id != null)
			return ResponseEntity.status(HttpStatus.OK).body(this.getService().findOne(Long.parseLong(id)));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping
	default public ResponseEntity<List<?>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.getService().findAll());
	}

	@PostMapping
	default public ResponseEntity<?> save(@RequestBody D dto) throws Exception {

		if (dto != null) {
			this.getService().save(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@PutMapping
	default public ResponseEntity<?> update(@RequestBody D dto) throws Exception {

		if (dto != null) {
			this.getService().save(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@DeleteMapping(value = "/{id}")
	default public ResponseEntity<?> delete(@PathVariable String id) throws Exception {

		if (id != null) {
			this.getService().delete(Long.parseLong(id));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
