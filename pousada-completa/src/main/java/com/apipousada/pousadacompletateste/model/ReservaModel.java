package com.apipousada.pousadacompletateste.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "reservas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A data de início é obrigatória")
    @Column(nullable = false)
    private String dataInicio;
    
    @NotBlank(message = "A data de fim é obrigatória")
    @Column(nullable = false)
    private String dataFim;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonProperty("pessoa")
    private CadastroModel pessoa;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    @JsonProperty("quarto")
    private QuartoModel quarto;

    public ReservaModel() {}

    public ReservaModel(String dataInicio, String dataFim, CadastroModel pessoa, QuartoModel quarto) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pessoa = pessoa;
        this.quarto = quarto;
    }
    
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

