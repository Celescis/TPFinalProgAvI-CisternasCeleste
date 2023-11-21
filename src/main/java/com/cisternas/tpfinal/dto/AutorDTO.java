package com.cisternas.tpfinal.dto;

import java.util.ArrayList;
import java.util.List;

public class AutorDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private List<LibroDTO> libros;

	public AutorDTO() {
		libros = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<LibroDTO> getLibros() {
		return libros;
	}

	public void setLibros(List<LibroDTO> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad=" + nacionalidad
				+ ", libros=" + libros + "]";
	}

}
