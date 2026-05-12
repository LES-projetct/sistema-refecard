package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.Produto;
import com.refecard.sistema.dto.ProdutoListagemDTO;
import com.refecard.sistema.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<ProdutoListagemDTO> listarProdutosTela() {

        return repository.findAll()
            .stream()
            .map(produto -> new ProdutoListagemDTO(
                produto.getId(),
                produto.getDescricao(),
                produto.getCodigoBarras(),
                produto.getValorCusto(),
                produto.getValorVenda(),
                produto.getTipoProduto()
            ))
            .toList();
    }
}