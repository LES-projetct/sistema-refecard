package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFantasia;

    @Column(unique = true, nullable = false)
    private String cnpj;

    // RELAÇÃO 1-N COM PRODUTO
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Produto> produtos = new HashSet<>();

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContaPagar> contasPagar = new HashSet<>();
}