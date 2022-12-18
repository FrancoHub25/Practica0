package com.practicas.responsive;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practicas.entities.Marca;

public interface MarcaResponsive extends JpaRepository<Marca, Integer> {

	@Query("select m from Marca m where m.nombre like ?1")
	public List<Marca> listarMarcaLike(String nombre);
	
	
}
