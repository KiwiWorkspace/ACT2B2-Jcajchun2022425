package com.juancajchun.act4_tienda.service;

import com.juancajchun.act4_tienda.entity.Producto;
import com.juancajchun.act4_tienda.exceptions.ResourcesNotFoundException;
import com.juancajchun.act4_tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(
                        "Producto con ID no encontrado: " + id
                ));
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setIdProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = obtenerPorId(id);

        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecioProducto(producto.getPrecioProducto());
        existente.setStockProducto(producto.getStockProducto());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Producto existente = obtenerPorId(id);
        productoRepository.delete(existente);
    }
}