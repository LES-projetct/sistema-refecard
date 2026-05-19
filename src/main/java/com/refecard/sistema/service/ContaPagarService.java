package com.refecard.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refecard.sistema.model.ContaPagar;
import com.refecard.sistema.repository.ContaPagarRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaPagarService {

    @Autowired
    private ContaPagarRepository repository;

    public ContaPagar salvar(ContaPagar contaPagar) {
        return repository.save(contaPagar);
    }

    public List<ContaPagar> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void pagarFornecedor(Long fornecedorId) {

        List<ContaPagar> contas = repository.findAll()
            .stream()
            .filter(c ->
                c.getFornecedor().getId().equals(fornecedorId)
                && c.getDataPagamento() == null
            )
            .toList();

        for (ContaPagar conta : contas) {
            conta.setDataPagamento(LocalDate.now());
            repository.save(conta);
        }
    }
}