package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pesoOuQuantidade;

    private Double valorCobrado;

    // RELAÇÃO N-1 COM PRODUTO (vários itens para um produto)
    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    // RELAÇÃO N-1 COM VENDA (vários itens para uma venda)
    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;
}