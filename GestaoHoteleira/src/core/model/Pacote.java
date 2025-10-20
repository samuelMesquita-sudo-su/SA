package core.model;

import base.util.Utilidades;

import java.util.Objects;

public class Pacote {

    // Atributos
    private Long id; // Identificador
    private String nome; // Obrigatório
    private Acomodacao acomodacao; // Obrigatório
    private Integer qtdDiarias; // Obrigatório
    private Double valorTotal; // Opcional

    // Construtor vazio
    public Pacote() {}

    // Construtor sem o id
    public Pacote(
        String nome,
        Acomodacao acomodacao,
        Integer qtdDiarias,
        Double valorTotal
    ) {
        this.nome = nome;
        this.acomodacao = acomodacao;
        this.qtdDiarias = qtdDiarias;
        this.valorTotal = valorTotal;
    }

    // Construtor com todos os atributos
    public Pacote(
        Long id,
        String nome,
        Acomodacao acomodacao,
        Integer qtdDiarias,
        Double valorTotal
    ) {
        this.id = id;
        this.nome = nome;
        this.acomodacao = acomodacao;
        this.qtdDiarias = qtdDiarias;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public Integer getQtdDiarias() {
        return qtdDiarias;
    }

    public void setQtdDiarias(Integer qtdDiarias) {
        this.qtdDiarias = qtdDiarias;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // Outros métodos

    @Override
    public String toString() {
        return "Id: " + id
           +  " | Nome: " + nome
           +  " | Acomodação: " + acomodacao.getId() + " - " + acomodacao.getNome()
           +  " | Quantidade de diárias: " + qtdDiarias
           +  " | Valor total: " + Utilidades.formatarValorMonetario(valorTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pacote pacote = (Pacote) o;
        return Objects.equals(id, pacote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
