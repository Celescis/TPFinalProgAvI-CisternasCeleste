package com.cisternas.tpfinal.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cisternas.tpfinal.model.Libro;

@Component
public class LibroMapper {

	//PARA PROTEGER LA ENTITY
		public Libro dtoToEntity(LibroDTO dto)
		{
			Libro entity = new Libro();
			
			entity.setId(dto.getId());
			entity.setTitulo(dto.getTitulo());
			entity.setPrecio(dto.getPrecio());
			entity.setEditorial(dto.getEditorial());
			entity.setAutor(dto.getAutor());
			
			return entity;
		}
		
		public LibroDTO entityToDto(Libro entity)
		{
			LibroDTO dto = new LibroDTO();

			dto.setId(entity.getId());
			dto.setTitulo(entity.getTitulo());
			dto.setPrecio(entity.getPrecio());
			dto.setEditorial(entity.getEditorial());
			dto.setAutor(entity.getAutor());

			return dto;
		}
		
		public List<LibroDTO> lstEntityToLstDto(List<Libro> entity)
		{
			List<LibroDTO> lstDto = new ArrayList<>();
			
			for(Libro ent : entity)
			{
				lstDto.add(this.entityToDto(ent));
			}

			return lstDto;
		}
}