package com.cisternas.tpfinal.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cisternas.tpfinal.model.Autor;
import com.cisternas.tpfinal.model.Libro;

@Component
public class AutorMapper {

	@Autowired
	LibroMapper libroMapper;

	public Autor dtoToEntity(AutorDTO dto) {
		Autor entity = new Autor();

		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setNacionalidad(dto.getNacionalidad());
		entity.setLibros(libroMapper.lstDtoToLstEntity(dto.getLibros()));

		/*
		 * for (LibroDTO libDto : dto.getLibros()) { Libro lib =
		 * libroMapper.dtoToEntity(libDto); entity.getLibros().add(lib); }
		 */
		return entity;
	}

	public AutorDTO entityToDto(Autor entity) {
		AutorDTO dto = new AutorDTO();

		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setNacionalidad(entity.getNacionalidad());
		dto.setLibros(libroMapper.lstEntityToLstDto(entity.getLibros()));

		/*
		 * for (Libro lib : entity.getLibros()) {
		 * 
		 * LibroDTO libDto = libroMapper.entityToDto(lib);
		 * 
		 * dto.getLibros().add(libDto); }
		 */

		return dto;
	}

	public List<AutorDTO> lstEntityToLstDto(List<Autor> entityList) {
		List<AutorDTO> dtoList = new ArrayList<>();

		for (Autor ent : entityList) {
			dtoList.add(this.entityToDto(ent));
		}

		return dtoList;
	}

	public List<Autor> lstDtoToLstEntity(List<AutorDTO> dtoList) {
		List<Autor> lst = new ArrayList<>();

		for (AutorDTO dto : dtoList) {
			lst.add(this.dtoToEntity(dto));
		}

		return lst;
	}
}
