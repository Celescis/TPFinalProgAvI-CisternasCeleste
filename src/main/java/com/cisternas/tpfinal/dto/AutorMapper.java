package com.cisternas.tpfinal.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cisternas.tpfinal.model.Autor;


@Component
public class AutorMapper {

	//PARA PROTEGER LA ENTITY
		public Autor dtoToEntity(AutorDTO dto)
		{
			Autor entity = new Autor();
			
			entity.setId(dto.getId());
			entity.setNombre(dto.getNombre());
			entity.setApellido(dto.getApellido());
			entity.setNacionalidad(dto.getNacionalidad());
			entity.setLibros(dto.getLibros());
			
			return entity;
		}
		
		public AutorDTO entityToDto(Autor entity)
		{
			AutorDTO dto = new AutorDTO();

			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setApellido(entity.getApellido());
			dto.setNacionalidad(entity.getNacionalidad());
			dto.setLibros(entity.getLibros());

			return dto;
		}
		
		public List<AutorDTO> lstEntityToLstDto(List<Autor> entity)
		{
			List<AutorDTO> lstDto = new ArrayList<>();
			
			for(Autor ent : entity)
			{
				lstDto.add(this.entityToDto(ent));
			}

			return lstDto;
		}
}
