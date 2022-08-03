package com.danielrodriguez.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.danielrodriguez.springboot.app.item.clientes.ProductoClienteRest;
import com.danielrodriguez.springboot.app.item.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductoClienteRest productoClienteRestFeign;

    @Override
    public List<Item> findAll() {
        return productoClienteRestFeign.listar().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(productoClienteRestFeign.detalle(id), cantidad);
    }
    
}
