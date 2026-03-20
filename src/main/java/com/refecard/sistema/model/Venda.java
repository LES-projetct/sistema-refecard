package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraVenda;

    private String formaPagamento;

    private Double valorTotal;

    private Double saldoAnterior;

    private Double saldoAtualizado;

    // RELAÇÃO N-1 COM CARTAO (muitas vendas para um cartão)
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @JsonBackReference
    private Cartao cartao;

    @OneToOne(mappedBy = "venda", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ContaReceber contaReceber;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ItemVenda> itens = new HashSet<>();
}