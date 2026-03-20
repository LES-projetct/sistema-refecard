package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class RecargaSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorAdicionado;

    private LocalDateTime dataRecarga;

    // RELAÇÃO N-1 COM CARTAO (várias recargas para um cartão)
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @JsonBackReference
    private Cartao cartao;
}