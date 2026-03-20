package com.refecard.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.refecard.sistema.model.usuario.Usuario;
import com.refecard.sistema.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint de teste
    @PostMapping("/teste")
    public Usuario criarUsuarioTeste() {
        return usuarioService.criarUsuarioTeste();
    }
}