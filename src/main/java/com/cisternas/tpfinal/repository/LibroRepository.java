package com.cisternas.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cisternas.tpfinal.model.Libro;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long>{

	List<Libro> findAllByTitulo(String titulo);
}
