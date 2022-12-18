package com.practicas.services;

import java.util.List;
import java.util.Optional;

import com.practicas.entities.Marca;

public interface MarcaService {
	
	public abstract Marca insertaActualizarMarca(Marca marca);
	public abstract List<Marca> listaMarcaNombreLike(String nombre);
	public abstract void eliminaMarca(int id);
	public abstract Optional<Marca> buscaMarca(int id);

}
