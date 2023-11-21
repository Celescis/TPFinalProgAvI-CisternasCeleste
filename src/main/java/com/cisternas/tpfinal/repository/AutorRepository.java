package com.cisternas.tpfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cisternas.tpfinal.model.Autor;

public interface AutorRepository  extends CrudRepository<Autor, Long>{

    List<Autor> findAllByApellidoOrderById(String apellido);
	
    @Query("select a from Autor a where a.nacionalidad = :nacionalidad")
    List<Autor> findAllByNacionalidad(String nacionalidad);
    
    @Query("select a from Autor a join a.libros l where l.titulo like :letra%")
    List<Autor> findAllByLibroTituloEmpiezaCon(String letra);
    
}
