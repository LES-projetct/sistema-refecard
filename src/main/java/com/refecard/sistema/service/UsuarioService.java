package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.usuario.Usuario;
import com.refecard.sistema.repository.UsuarioRepository;
import com.refecard.sistema.dto.UsuarioListagemDTO;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return usuario;
    }

    public List<UsuarioListagemDTO> listarUsuariosTela() {

        return repository.findAll()
            .stream()
            .map(usuario -> {

                String codigoCartaoRfid = "";
                Double saldo = 0.0;
                String status = "Inativo";

                if (usuario.getCartao() != null) {

                    codigoCartaoRfid = usuario.getCartao().getCodigoCartaoRfid();

                    saldo = usuario.getCartao().getSaldo();

                    Boolean bloqueado =
                        usuario.getCartao().getAcessoBloqueado();

                    status = Boolean.TRUE.equals(bloqueado)
                        ? "Bloqueado"
                        : "Ativo";
                }

                return new UsuarioListagemDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    codigoCartaoRfid,
                    saldo,
                    status
                );
            })
            .toList();
    }
}