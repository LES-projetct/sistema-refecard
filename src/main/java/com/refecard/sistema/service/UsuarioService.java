package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.usuario.Usuario;
import com.refecard.sistema.model.usuario.UsuarioRole;
import com.refecard.sistema.model.usuario.Role;
import com.refecard.sistema.repository.UsuarioRepository;
import com.refecard.sistema.dto.UsuarioListagemDTO;
import com.refecard.sistema.dto.UsuarioCreateDTO;
import com.refecard.sistema.model.Cartao;

import java.util.List;
import java.util.UUID;

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

    public Usuario criarUsuarioComCartao(UsuarioCreateDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setSenha(dto.getSenha());
        usuario.setDataNascimento(dto.getDataNascimento());

        UsuarioRole role = new UsuarioRole();
        role.setNomeRole(dto.getRole());
        role.setUsuario(usuario);

        usuario.getRoles().add(role);

        Cartao cartao = new Cartao();
        cartao.setCodigoCartaoRfid(UUID.randomUUID().toString());
        cartao.setSaldo(0.0);
        cartao.setLimiteCredito(0.0);
        cartao.setAcessoBloqueado(false);
        cartao.setUsuario(usuario);

        usuario.setCartao(cartao);

        return repository.save(usuario);
    }
}