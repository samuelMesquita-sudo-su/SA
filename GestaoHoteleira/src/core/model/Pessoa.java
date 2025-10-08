package core.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {

    /// Atributos ///
    private Long idPessoa;              // Obrigatório
    private String nomeCompleto;        // Obrigatório
    private LocalDate dataNascimento;   // Obrigatório
    private String documento;           // Obrigatório
    private String pais;                // Opcional
    private String estado;              // Opcional
    private String cidade;              // Opcional

    // Construtor vazio
    public Pessoa(){}

    // Construtor sem id
    public Pessoa(String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.documento = documento;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Construtor completo
    public Pessoa(Long idPessoa, String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.documento = documento;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Get´s & Set´s
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
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

    @Override
    public String toString() {
        return "ID Pessoa{ " +
                "   idPessoa=" + idPessoa +
                " |     Nome completo='" + nomeCompleto + '\'' +
                " |     Data nascimento=" + dataNascimento +
                " |     Documento='" + documento + '\'' +
                " |     País='" + pais + '\'' +
                " |     Estado='" + estado + '\'' +
                " |     Cidade='" + cidade + '\'' +
                "   }";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(idPessoa, pessoa.idPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPessoa);
    }
}
