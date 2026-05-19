package com.refecard.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.refecard.sistema.model.Fornecedor;
import com.refecard.sistema.service.FornecedorService;
import com.refecard.sistema.dto.FornecedorPagamentoDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @PostMapping
    public Fornecedor criar(@RequestBody Fornecedor fornecedor) {
        return service.salvar(fornecedor);
    }

    @GetMapping
    public List<Fornecedor> listar() {
        return service.listar();
    }

    @GetMapping("/pagamentos")
    public List<FornecedorPagamentoDTO> listarParaTelaPagamento() {

        return service.listarParaTelaPagamento();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}