package com.apipousada.pousadacompletateste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quartos")
public class QuartoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private String descricaoDetalhada;

    @Column(nullable = false)
    private int quantidadeDisponivel;

    @Column(nullable = false)
    private String imagem; // Nova coluna para a imagem em formato Base64

    // construtor padrão
    public QuartoModel() {}

    // construtor com parâmetros
    public QuartoModel(String descricao, double preco, String descricaoDetalhada, int quantidadeDisponivel, String imagem) {
        this.descricao = descricao;
        this.preco = preco;
        this.descricaoDetalhada = descricaoDetalhada;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.imagem = imagem;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
