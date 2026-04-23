package com.juancajchun.act4_tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "Debe ingresar el nombre del Producto")
    @Size(min = 2, max = 60, message = "El nombre del Producto debe tener mínimo 2 caracteres y máximo 60.")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "Debe darle un precio al Producto")
    @DecimalMin(value = "1.0", message = "El precio del Producto debe ser mínimo Q1.")
    @Column(name = "precio_producto")
    private Double precioProducto;

    @NotNull(message = "El stock del Producto no debe estar vacío")
    @Min(value = 1, message = "El stock del Producto debe ser mínimo 1.")
    @Max(value = 1000, message = "El stock del Producto no puede exceder 1000.")
    @Column(name = "stock_producto")
    private Integer stockProducto;

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Double getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(Double precioProducto) { this.precioProducto = precioProducto; }

    public Integer getStockProducto() { return stockProducto; }
    public void setStockProducto(Integer stockProducto) { this.stockProducto = stockProducto; }
}