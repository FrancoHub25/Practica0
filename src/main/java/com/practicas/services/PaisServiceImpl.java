package com.practicas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.entities.Pais;
import com.practicas.responsive.PaisResponsive;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisResponsive repo;

	@Override
	public List<Pais> listaPaises() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	

}
