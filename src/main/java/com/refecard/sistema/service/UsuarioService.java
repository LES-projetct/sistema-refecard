package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.usuario.Usuario;
import com.refecard.sistema.model.usuario.UsuarioRole;
import com.refecard.sistema.model.usuario.Role;
import com.refecard.sistema.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuarioTeste() {

        Usuario usuario = new Usuario();
        usuario.setNome("João Teste");
        usuario.setEmail("joao.teste@email.com");
        usuario.setCpf("12345678900");
        usuario.setSenha("123456");
        usuario.setDataNascimento(java.time.LocalDate.of(2000, 1, 1));

        // Criando ROLE
        UsuarioRole role = new UsuarioRole();
        role.setNomeRole(Role.CLIENTE);
        role.setUsuario(usuario);

        usuario.getRoles().add(role);

        return usuarioRepository.save(usuario);
    }
}