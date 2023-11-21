package com.cisternas.tpfinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cisternas.tpfinal.dto.AutorDTO;
import com.cisternas.tpfinal.dto.AutorMapper;
import com.cisternas.tpfinal.model.Autor;
import com.cisternas.tpfinal.model.Libro;
import com.cisternas.tpfinal.repository.AutorRepository;
import com.cisternas.tpfinal.services.AutorService;

@RestController
@RequestMapping("/au")
public class AutorController {

	@Autowired
	AutorRepository autorRepository;
	@Autowired
	AutorMapper autorMapper;
	@Autowired
	AutorService autorService;

	// GET - UN AUTOR
	@GetMapping("/autor/{id}")

	public ResponseEntity<?> obtenerAutor(@PathVariable Long id) {
		try {
			Optional<Autor> pOpt = autorRepository.findById(id);

			if (pOpt.isPresent()) {
				return new ResponseEntity<AutorDTO>(autorMapper.entityToDto(pOpt.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Autor no encontrado con id " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo el autor: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET - LISTA DE AUTORES GENERAL
	@GetMapping("/autores")
	public ResponseEntity<?> obtenerAutores() {
		try {

			List<Autor> auList = (List<Autor>) autorRepository.findAll();
			if (auList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron autores", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<AutorDTO>>(autorMapper.lstEntityToLstDto(auList), HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo la lista de autores: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// GET - LISTA DE AUTORES POR APELLIDO
	@GetMapping("/autores/apellido/{apellido}")
	public ResponseEntity<?> obtenerAutoresPorApellidoOrderById(@PathVariable String apellido) {
		try {

			List<Autor> auList = (List<Autor>) autorRepository.findAllByApellidoOrderById(apellido);

			if (auList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron autores de apellido: " + apellido,
						HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<AutorDTO>>(autorMapper.lstEntityToLstDto(auList), HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo la lista de autores: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// GET - LISTA DE AUTORES POR NACIONALIDAD
	@GetMapping("/autores/nacionalidad/{nacionalidad}")
	public ResponseEntity<?> obtenerAutoresPorNacionalidad(@PathVariable String nacionalidad) {
		try {

			List<Autor> auList = (List<Autor>) autorRepository.findAllByNacionalidad(nacionalidad);

			if (auList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron autores de nacionalidad: " + nacionalidad,
						HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<AutorDTO>>(autorMapper.lstEntityToLstDto(auList), HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo la lista de autores: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// GET - LISTA DE AUTORES POR LIBRO EMPIEZA CON...
	@GetMapping("/autores/libro/{letra}")
	public ResponseEntity<?> obtenerAutoresPorLibroEmpiezaCon(@PathVariable String letra) {
		try {

			List<Autor> auList = (List<Autor>) autorRepository.findAllByLibroTituloEmpiezaCon(letra);

			if (auList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron autores de nacionalidad: " + letra,
						HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<AutorDTO>>(autorMapper.lstEntityToLstDto(auList), HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo la lista de autores: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// POST - CREAR UN AUTOR
	@PostMapping("/autor")
	public ResponseEntity<?> crearAutor(@RequestBody @Validated Autor au) {
		try {
			if(au.getLibros().size()>0)
			{
				for (Libro lib : au.getLibros()) {
					lib.setAutor(au);
				}
				
				autorRepository.save(au);
				
				return new ResponseEntity<String>("Se creo el autor", HttpStatus.OK);				
			}else {
				return new ResponseEntity<String>("Debe ingresar un libro", HttpStatus.CONFLICT);	
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error creando el autor: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// PUT - MODIFICAR UN AUTOR
	@PutMapping("/autor")
	public ResponseEntity<?> modificarAutor(@RequestBody @Validated AutorDTO auDto) {
		if (autorService.verificarLibrosAutor(auDto)) {

			return new ResponseEntity<String>("Se modifico el autor", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Autor no encontrado con id " + auDto.getId(), HttpStatus.NOT_FOUND);
		}
	}

	// DELETE - BORRAR UN AUTOR
	@DeleteMapping("/autor")
	public ResponseEntity<?> eliminarAutor(@RequestBody @Validated AutorDTO auDto) {
		try {
			Optional<Autor> pOpt = autorRepository.findById(auDto.getId());

			if (pOpt.isPresent()) {

				autorRepository.delete(pOpt.get());

				return new ResponseEntity<String>("Se elimino el autor", HttpStatus.OK);

			} else {

				return new ResponseEntity<String>("Autor no encontrado con id " + pOpt.get().getId(),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error eliminando al autor: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
