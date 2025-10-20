package core.model;

import base.util.Utilidades;
import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {

    // Atributos
    private Long idPessoa; // Identificador
    private String nomeCompleto; // Obrigatório
    private LocalDate dataNascimento; // Obrigatório
    private String documento; // Obrigatório
    private String pais; // Opcional
    private String estado; // Opcional
    private String cidade; // Opcional

    // Construtor vazio
    public Pessoa() {}

    // Construtor sem o id
    public Pessoa(
        String nomeCompleto,
        LocalDate dataNascimento,
        String documento,
        String pais,
        String estado,
        String cidade
    ) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.documento = documento;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Construtor com todos os atributos
    public Pessoa(
            Long idPessoa,
            String nomeCompleto,
            LocalDate dataNascimento,
            String documento,
            String pais,
            String estado,
            String cidade
    ) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.documento = documento;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Getters e Setters

    public Long getId() {
        return idPessoa;
    }

    public void setId(Long id) {
        this.idPessoa = idPessoa;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Outros métodos

    @Override
    public String toString() {
        return "Id: " + idPessoa +
            " | Nome completo: " + nomeCompleto +
            " | Data de nascimento: " + Utilidades.formatarDataBR(dataNascimento) +
            " | Documento: " + documento +
            " | País: " + pais +
            " | Estado: " + estado +
            " | Cidade: " + cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return Objects.equals(idPessoa, pessoa.idPessoa);
    }

    @Override
    public int hashCode() {
        return idPessoa != null ? idPessoa.hashCode() : 0;
    }

}
