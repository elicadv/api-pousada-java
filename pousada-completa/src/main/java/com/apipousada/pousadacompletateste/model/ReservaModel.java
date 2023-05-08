package com.apipousada.pousadacompletateste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dataInicio;

    @Column(nullable = false)
    private String dataFim;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private CadastroModel pessoa;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private QuartoModel quarto;

    // construtor padrão
    public ReservaModel() {}

    // construtor com parâmetros
    public ReservaModel(String dataInicio, String dataFim, CadastroModel pessoa, QuartoModel quarto) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pessoa = pessoa;
        this.quarto = quarto;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public CadastroModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(CadastroModel pessoa) {
        this.pessoa = pessoa;
    }

    public QuartoModel getQuarto() {
        return quarto;
    }

    public void setQuarto(QuartoModel quarto) {
        this.quarto = quarto;
    }
}

