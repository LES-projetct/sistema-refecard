package com.refecard.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.refecard.sistema.model.ContaPagar;
import com.refecard.sistema.service.ContaPagarService;

import java.util.List;

@RestController
@RequestMapping("/contas-pagar")
public class ContaPagarController {

    @Autowired
    private ContaPagarService service;

    @PostMapping
    public ContaPagar criar(@RequestBody ContaPagar contaPagar) {
        return service.salvar(contaPagar);
    }

    @PostMapping("/fornecedor/{fornecedorId}/pagar")
    public void pagarFornecedor(@PathVariable Long fornecedorId) {
        service.pagarFornecedor(fornecedorId);
    }

    @GetMapping
    public List<ContaPagar> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}