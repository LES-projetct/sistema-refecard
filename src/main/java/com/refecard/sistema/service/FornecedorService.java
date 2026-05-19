package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.ContaPagar;
import com.refecard.sistema.model.Fornecedor;
import com.refecard.sistema.repository.FornecedorRepository;
import com.refecard.sistema.dto.FornecedorPagamentoDTO;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Fornecedor salvar(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public List<Fornecedor> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<FornecedorPagamentoDTO> listarParaTelaPagamento() {

        return repository.findAll()
            .stream()

            //Filtro fornecedores
            .filter(fornecedor ->
                fornecedor.getContasPagar()
                    .stream()
                    .anyMatch(conta -> conta.getDataPagamento() == null)
            )

            .map(fornecedor -> {

                Double saldo = fornecedor.getContasPagar()
                    .stream()
                    .filter(conta -> conta.getDataPagamento() == null)
                    .mapToDouble(ContaPagar::getValorPagar)
                    .sum();

                return new FornecedorPagamentoDTO(
                    fornecedor.getId(),
                    fornecedor.getNomeFantasia(),
                    fornecedor.getCnpj(),
                    saldo
                );
            })
            .toList();
    }
}