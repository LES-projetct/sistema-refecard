package com.refecard.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.refecard.sistema.dto.LoginRequest;
import com.refecard.sistema.dto.UsuarioCreateDTO;
import com.refecard.sistema.dto.UsuarioListagemDTO;
import com.refecard.sistema.model.usuario.Usuario;
import com.refecard.sistema.service.UsuarioService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @PostMapping("/criar-com-cartao")
    public Usuario criarComCartao(@RequestBody UsuarioCreateDTO dto) {
        return service.criarUsuarioComCartao(dto);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/cpf/{cpf}")
    public Usuario buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf);
    }

    @GetMapping("/listagem")
    public List<UsuarioListagemDTO> listarUsuariosTela() {

        return service.listarUsuariosTela();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {

            Usuario usuario = service.login(
                request.getEmail(),
                request.getSenha()
            );

            return ResponseEntity.ok(usuario);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}