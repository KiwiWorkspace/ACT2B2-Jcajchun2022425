package com.juancajchun.act4_tienda.repository;

import com.juancajchun.act4_tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
