package com.cisternas.tpfinal.dto;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cisternas.tpfinal.model.Autor;
import com.cisternas.tpfinal.model.Libro;
import com.cisternas.tpfinal.repository.AutorRepository;

@Component
public class LibroMapper {
	
	@Autowired
	AutorRepository autorRepository;

	public Libro dtoToEntity(LibroDTO dto) {
		Libro entity = new Libro();

	    entity.setId(dto.getId());
	    entity.setTitulo(dto.getTitulo());
	    entity.setPrecio(dto.getPrecio());
	    entity.setEditorial(dto.getEditorial());

	    if (dto.getAutor() != null) {
	        Optional<Autor> opt = autorRepository.findById(dto.getAutor().getId());
	        
	        entity.setAutor(opt.get());
	    }
		return entity;
	}

	public LibroDTO entityToDto(Libro entity) {
		LibroDTO dto = new LibroDTO();

		dto.setId(entity.getId());
		dto.setTitulo(entity.getTitulo());
		dto.setPrecio(entity.getPrecio());
		dto.setEditorial(entity.getEditorial());

		if (entity.getAutor() != null) {
			AutorDTO autorDTO = new AutorDTO();

			autorDTO.setId(entity.getAutor().getId());
			autorDTO.setNombre(entity.getAutor().getNombre());
			autorDTO.setApellido(entity.getAutor().getApellido());
			autorDTO.setNacionalidad(entity.getAutor().getNacionalidad());

			dto.setAutor(autorDTO);
		}

		return dto;
	}

	public List<LibroDTO> lstEntityToLstDto(List<Libro> entity) {
		List<LibroDTO> lstDto = new ArrayList<>();

		for (Libro ent : entity) {
			lstDto.add(this.entityToDto(ent));
		}

		return lstDto;
	}

	public List<Libro> lstDtoToLstEntity(List<LibroDTO> dto) {
		List<Libro> lst = new ArrayList<>();

		for (LibroDTO ent : dto) {
			lst.add(this.dtoToEntity(ent));
		}

		return lst;
	}
}