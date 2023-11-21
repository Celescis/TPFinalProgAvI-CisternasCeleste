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

import com.cisternas.tpfinal.dto.LibroDTO;
import com.cisternas.tpfinal.dto.LibroMapper;
import com.cisternas.tpfinal.model.Libro;
import com.cisternas.tpfinal.repository.LibroRepository;

@RestController
@RequestMapping("/li")
public class LibroController {

	@Autowired
	LibroRepository libroRepository;
	@Autowired
	LibroMapper libroMapper;

	// GET - UN LIBRO
	@GetMapping("/libro/{id}")
	public ResponseEntity<?> obtenerLibro(@PathVariable Long id) {
		try {
			Optional<Libro> pOpt = libroRepository.findById(id);

			if (pOpt.isPresent()) {
				return new ResponseEntity<LibroDTO>(libroMapper.entityToDto(pOpt.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Libro no encontrado con id " + id, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo el libro: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET - LISTA DE LIBROS GENERAL
	@GetMapping("/libros")
	public ResponseEntity<?> obtenerLibros() {
		try {

			List<Libro> liList = (List<Libro>) libroRepository.findAll();
			if (liList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron libros", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<LibroDTO>>(libroMapper.lstEntityToLstDto(liList), HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo la lista de libros: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// GET - UN LIBRO POR TITULO
	@GetMapping("/libro/titulo/{titulo}")
	public ResponseEntity<?> obtenerLibrosPorTitulo(@PathVariable String titulo) {
		try {
			List<Libro> liList = (List<Libro>) libroRepository.findAllByTitulo(titulo);

			if (liList.isEmpty()) {
				return new ResponseEntity<String>("No se encontraron libros", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<List<LibroDTO>>(libroMapper.lstEntityToLstDto(liList), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error obteniendo el libro: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// POST - CREAR UN LIBRO
	@PostMapping("/libro")
	public ResponseEntity<?> crearLibro(@RequestBody @Validated Libro li) {
		try {
			if (li != null) {

				libroRepository.save(li);

				return new ResponseEntity<String>("Se creo el libro", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Debe ingresar un libro", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error creando el libro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// PUT - MODIFICAR UN LIBRO
	@PutMapping("/libro")
	public ResponseEntity<?> modificarAutor(@RequestBody @Validated LibroDTO liDto) {
		Optional<Libro> pOpt = this.libroRepository.findById(liDto.getId());

		if (pOpt.isPresent()) {
			libroRepository.save(libroMapper.dtoToEntity(liDto));
			return new ResponseEntity<String>("Se modifico el libro", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Libro no encontrado con id " + liDto.getId(), HttpStatus.NOT_FOUND);
		}
	}

	// DELETE - BORRAR UN LIBRO
	@DeleteMapping("/libro")
	public ResponseEntity<?> eliminarLibro(@RequestBody @Validated LibroDTO liDto) {
		try {
			Optional<Libro> pOpt = libroRepository.findById(liDto.getId());

			if (pOpt.isPresent()) {

				libroRepository.delete(pOpt.get());

				return new ResponseEntity<String>("Se elimino el libro", HttpStatus.OK);

			} else {

				return new ResponseEntity<String>("Libro no encontrado con id " + pOpt.get().getId(),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error eliminando al libro: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
