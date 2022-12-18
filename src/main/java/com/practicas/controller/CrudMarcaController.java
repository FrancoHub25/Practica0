package com.practicas.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.entities.Marca;
import com.practicas.services.MarcaService;
import com.practicas.util.Constantes;

@RestController
@RequestMapping("/rest/marca")
public class CrudMarcaController {

	@Autowired
	private MarcaService service;
	
	@GetMapping("/listaMarcaPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarcaPorNombreLike(@PathVariable("nom") String nom) {
		List<Marca> lista = null;
		try {
			if(nom.equals("todos")) {
				lista = service.listaMarcaNombreLike("%");
			} else {
				lista = service.listaMarcaNombreLike("%" + nom + "%");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(lista);
		
	}
	
	@PostMapping("/registrarMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		
		try {
			obj.setIdMarca(0);
			obj.setFechaRegistro(new Date());
			obj.setFechaVigencia(new Date());
			obj.setEstado(1);
			Marca objSalida =  service.insertaActualizarMarca(obj);
			if(objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizarMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizarMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		
		try {
			Marca objSalida =  service.insertaActualizarMarca(obj);
			if(objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminarMarca/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarMarca(@PathVariable("id") int id) {
		
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Marca> opt = service.buscaMarca(id);
			if(opt.isPresent()) {
				service.eliminaMarca(id);
				Optional<Marca> optMarca = service.buscaMarca(id);
				if (optMarca.isEmpty()) {
					salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
				} else {
					salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
				}
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ELI_NO_EXISTE_ID);
			}
		} catch(Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
}
