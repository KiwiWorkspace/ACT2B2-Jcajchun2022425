package com.juancajchun.act4_tienda.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@JsonPropertyOrder({
        "idUsuario",
        "nombreUsuario",
        "apellidoUsuario",
        "edadUsuario"
})
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre del usuario no puede ir vacío.")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 y 60 caracteres.")
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NotBlank(message = "El apellido del usuario no puede ir vacío.")
    @Size(min = 2, max = 60, message = "El apellido debe tener entre 2 y 60 caracteres.")
    @Column(name = "apellido_usuario")
    private String apellidoUsuario;

    @NotNull(message = "La edad no puede ir vacía.")
    @Min(value = 1, message = "La edad debe ser mayor o igual a 1.")
    @Max(value = 120, message = "La edad debe ser menor o igual a 120.")
    @Column(name = "edad_usuario")
    private Integer edadUsuario;

    @NotBlank(message = "La contraseña no puede ir vacía.")
    @Size(min = 4, max = 100, message = "La contraseña debe tener entre 4 y 100 caracteres.")
    @Column(name = "password")
    private String password;


    public Usuario(){

    }

    public Usuario(String nombreUsuario, String apellidoUsuario, Integer edadUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.edadUsuario = edadUsuario;
        this.password = password;
    }


    // Getters y setters

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getApellidoUsuario() { return apellidoUsuario; }
    public void setApellidoUsuario(String apellidoUsuario) { this.apellidoUsuario = apellidoUsuario; }

    public Integer getEdadUsuario() { return edadUsuario; }
    public void setEdadUsuario(Integer edadUsuario) { this.edadUsuario = edadUsuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}