package com.refecard.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.refecard.sistema.model.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);
}