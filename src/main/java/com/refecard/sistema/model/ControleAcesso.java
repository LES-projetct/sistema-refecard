package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class ControleAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraEntrada;

    private LocalDateTime dataHoraSaida;

    // RELAÇÃO N-1 COM CARTAO (muitos acessos para um cartão)
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @JsonBackReference
    private Cartao cartao;
}