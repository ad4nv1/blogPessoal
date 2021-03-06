package com.example.postagem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.postagem.model.Tema;
import com.example.postagem.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAllTema(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Tema>> findByIdTema(@PathVariable("id") Long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Tema>> getByTitulo(@PathVariable("titulo") String titulo){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(titulo));
	}
	

	@PostMapping
	public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema){
		return repository.findById(tema.getId())
		        .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema)))
		        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Tema> tema = repository.findById(id);
        if(tema.isEmpty())
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        repository.deleteById(id);
	}

}
