package com.danielrodriguez.springboot.app.productos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.danielrodriguez.springboot.app.productos.models.entity.Producto;
import com.danielrodriguez.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	@Autowired
	private Environment environment;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto -> {producto.setPort(port); return producto;}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id)  {
		Producto producto = productoService.findById(id);
		producto.setPort(port);

		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return producto;
	}
}
