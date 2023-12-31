package com.cisternas.tpfinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisternas.tpfinal.dto.AutorDTO;
import com.cisternas.tpfinal.dto.AutorMapper;
import com.cisternas.tpfinal.model.Autor;
import com.cisternas.tpfinal.model.Libro;
import com.cisternas.tpfinal.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorMapper autorMapper;
	@Autowired
	AutorRepository autorRepository;

	public Boolean verificarLibrosAutor(AutorDTO auDto) {
		Autor auNuevo = autorMapper.dtoToEntity(auDto);
	    Optional<Autor> pOpt = autorRepository.findById(auNuevo.getId());
	    
	    if (pOpt.isPresent()) {
	        Autor autorExistente = pOpt.get();
	        List<Libro> librosRecibidos = autorMapper.dtoToEntity(auDto).getLibros();
	        List<Libro> librosAutor = autorExistente.getLibros();
	        List<Libro> librosActualizados = new ArrayList<>();
	        
	        //SETEO CAMBIOS DE LO QUE VIENE POR POSTMAN AL AUTOR
	        autorExistente.setNombre(auNuevo.getNombre());
	        autorExistente.setApellido(auNuevo.getApellido());
	        autorExistente.setNacionalidad(auNuevo.getNacionalidad());

	        //BUSCO EN LA LISTA DE LIBROS RECIBIDOS POR POSTMAN Y COMPARO CON LA LISTA QUE YA TENIA ESE AUTOR
	        for (Libro libroRecibido : librosRecibidos) {
	            boolean libroExistente = false;

	            for (Libro libroAutor : librosAutor) {
	            		//SI EXISTE UN LIBRO CON EL MISMO TITULO MODIFICO SUS OTROS PARAMETROS
	                if (libroAutor.getTitulo().equalsIgnoreCase(libroRecibido.getTitulo())) {

	                    libroAutor.setPrecio(libroRecibido.getPrecio());
	                    libroAutor.setEditorial(libroRecibido.getEditorial());
	                    librosActualizados.add(libroAutor);
	                    libroExistente = true;
	                    break;
	                }
	            }
	            //SI NO EXISTE EL LIBRO LO AGREGO
	            if (!libroExistente) {
	                libroRecibido.setAutor(autorExistente);
	                librosActualizados.add(libroRecibido);
	            }
	        }

	        autorExistente.setLibros(librosActualizados);
	        autorRepository.save(autorExistente);
	        return true;
	    } else {
	        return false;
	    }
	}


}
