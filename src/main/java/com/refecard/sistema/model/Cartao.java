package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.refecard.sistema.model.usuario.Usuario;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoCartaoRfid;

    private Double limiteCredito;

    private Double saldo;

    private LocalDateTime dataUltimaCompra;

    private Boolean acessoBloqueado;

    // RELAÇÃO 1-1 COM USUARIO
    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ControleAcesso> acessos = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Venda> vendas = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<RecargaSaldo> recargas = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContaReceber> contasReceber = new HashSet<>();
}
