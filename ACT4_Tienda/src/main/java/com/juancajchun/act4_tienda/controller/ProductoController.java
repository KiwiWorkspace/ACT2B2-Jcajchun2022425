package com.juancajchun.act4_tienda.controller;

import com.juancajchun.act4_tienda.entity.Producto;
import com.juancajchun.act4_tienda.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    // Constructor limpio — antes tenía dos parámetros iguales y nunca asignaba correctamente
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/get")
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }

    // Se eliminó el @GetMapping("/post") que estaba encima — causaba conflicto de mapeos
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@Valid @RequestBody Producto producto) {
        return productoService.crear(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Integer id,
                               @Valid @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
    }
}