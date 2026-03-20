package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(unique = true)
    private String codigoBarras;

    private String tipoProduto;

    private Double valorCusto;

    private Double valorVenda;

    // RELAÇÃO 1-N COM HISTORICO PRECO QUILO
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<HistoricoPrecoQuilo> historicoPrecos = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ItemVenda> itensVenda = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    @JsonBackReference
    private Fornecedor fornecedor;
}