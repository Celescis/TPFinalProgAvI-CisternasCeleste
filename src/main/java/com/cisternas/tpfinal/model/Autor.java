package com.cisternas.tpfinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "sys_autor")
public class Autor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255785426510877859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "au_id")
	private Long id;

	@NotBlank(message="Debe ingresar el nombre")
	@Column(name = "au_nombre")
	private String nombre;

	@NotBlank(message="Debe ingresar el apellido")
	@Column(name = "au_apellido")
	private String apellido;

	@NotBlank(message="Debe ingresar la nacionalidad")
	@Column(name = "au_nacionalidad")
	private String nacionalidad;

	// CON CASCADE ALL DIGO QUE CUALQUIER ENTIDAD A LA QUE ME REFIERA EN ESTE
	// ATRIBUTO, QUE HAGA TODO (MODIFICAR, BORRAR, CREAR)
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Libro> libros;

	public Autor() {
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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad=" + nacionalidad
				+ ", libros=" + libros + "]";
	}

}
