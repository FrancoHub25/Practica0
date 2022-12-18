package com.practicas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.entities.Marca;
import com.practicas.responsive.MarcaResponsive;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaResponsive repo; 
	
	@Override
	public Marca insertaActualizarMarca(Marca marca) {
		// TODO Auto-generated method stub
		return repo.save(marca);
	}

	@Override
	public List<Marca> listaMarcaNombreLike(String nombre) {
		// TODO Auto-generated method stub
		return repo.listarMarcaLike(nombre);
	}

	@Override
	public void eliminaMarca(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id); 
	}

	@Override
	public Optional<Marca> buscaMarca(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

}
