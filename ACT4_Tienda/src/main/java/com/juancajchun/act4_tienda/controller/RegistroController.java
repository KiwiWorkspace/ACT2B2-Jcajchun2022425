package com.juancajchun.act4_tienda.controller;

import com.juancajchun.act4_tienda.entity.Usuario;
import com.juancajchun.act4_tienda.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String nombreUsuario,
            @RequestParam String apellidoUsuario,
            @RequestParam int edadUsuario,
            @RequestParam String password,
            @RequestParam String passwordcheck
    )

    {

        if (!password.equals(passwordcheck)) {
            return "redirect:/register?error=password";
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setApellidoUsuario(apellidoUsuario);
        usuario.setEdadUsuario(edadUsuario);
        usuario.setPassword(passwordEncoder.encode(password)); // 🔑 Encriptar

        usuarioRepository.save(usuario);

        return "redirect:/login?success";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
}