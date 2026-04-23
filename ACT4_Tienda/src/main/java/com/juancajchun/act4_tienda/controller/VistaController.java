package com.juancajchun.act4_tienda.controller;

import com.juancajchun.act4_tienda.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    private final ProductoService productoService;

    public VistaController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String inicio(Model model) {
        model.addAttribute("productos", java.util.List.of());
        return "home";
    }
}