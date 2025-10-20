package core.model;

import base.util.Utilidades;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente extends Pessoa {

    // Atributos
    private Long id; // Identificador
    private Boolean fidelidade; // Obrigatório
    private String observacao; // Opcional

    // Construtor vazio
    public Cliente() {}

    // Construtor sem o id
    public Cliente(
        String nomeCompleto,
        LocalDate dataNascimento,
        String documento,
        String pais,
        String estado,
        String cidade,
        Boolean fidelidade,
        String observacao
    ) {
        super(nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.fidelidade = fidelidade;
        this.observacao = observacao;
    }

    // Construtor com todos os atributos
    public Cliente(
        Long idPessoa,
        String nomeCompleto,
        LocalDate dataNascimento,
        String documento,
        String pais,
        String estado,
        String cidade,
        Long id,
        Boolean fidelidade,
        String observacao
    ) {
        super(idPessoa, nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.id = id;
        this.fidelidade = fidelidade;
        this.observacao = observacao;
    }

    // Getters e Setters

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(Boolean fidelidade) {
        this.fidelidade = fidelidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // Outros métodos

    @Override
    public String toString() {
        return "Id: " + id
                + " | Nome completo: " + super.getNomeCompleto()
                + " | Data de Nascimento: " + Utilidades.formatarDataBR(super.getDataNascimento())
                + " | Documento: " + super.getDocumento()
                + " | País: " + super.getPais()
                + " | Estado: " + super.getEstado()
                + " | Cidade: " + super.getCidade()
                + " | Fidelidade: " + (fidelidade ? "Sim" : "Não")
                + " | Observação: " + observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

}
