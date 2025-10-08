package core.model;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente extends Pessoa{

    // Atributos
    private Long id;                // Obrigatório //
    private Boolean fidelidade;     // Obrigatório //
    private String observacao;      // Opcional    //

    public Cliente() {}

    public Cliente(String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade, Boolean fidelidade, String observacao) {
        super(nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.fidelidade = fidelidade;
        this.observacao = observacao;
    }

    public Cliente(Long idPessoa, String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade, Long id, Boolean fidelidade, String observacao) {
        super(idPessoa, nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.id = id;
        this.fidelidade = fidelidade;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return super.toString()
                +"\n"
                +"\tCliente{    " +
                "   id=" + id +
                " |     fidelidade=" + fidelidade +
                " |     observacao='" + observacao + '\'' +
                "   }";
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
