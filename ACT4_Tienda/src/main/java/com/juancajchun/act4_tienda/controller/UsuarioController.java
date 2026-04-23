package com.juancajchun.act4_tienda.controller;

import com.juancajchun.act4_tienda.entity.Usuario;
import com.juancajchun.act4_tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modeEdicion", false);
        return "usuario-formulario";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuario") Usuario usuario,
                        BindingResult result,
                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modeEdicion", false);
            return "usuario-formulario";
        }

        usuarioService.crear(usuario);
        return "redirect:/home";
    }

    @GetMapping("/eliminar")
    public String mostrarFormularioEliminar() {
        return "eliminar-formulario";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/home";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("id") Integer id, Model model) {
        // Traer el usuario existente por id
        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("modeEdicion", true);
        return "editar-formulario";
    }

    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("usuario") Usuario usuario,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modeEdicion", true);
            return "editar-formulario";
        }

        // Llama a actualizar con la firma correcta
        usuarioService.actualizar(usuario.getIdUsuario(), usuario);
        return "redirect:/home";
    }

}

