package com.cisternas.tpfinal.model;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "sys_libro")
public class Libro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1732186000216166424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lib_id")
	private Long id;
	
	@NotBlank
	@Column(name = "lib_titulo")
	private String titulo;
	
	@Column(name = "lib_precio")
	private Float precio;
	
	@NotBlank
	@Column(name = "lib_editorial")
	private String editorial;

	@ManyToOne
	@JoinColumn(name = "au_id")
	private Autor autor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", editorial=" + editorial + ", autor="
				+ autor + "]";
	}
	
	
}
