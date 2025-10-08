package core.model;

import java.time.LocalDate;
import java.util.Objects;

public class Funcionario extends Pessoa{

    // Atributos
    private Long id;        // Obrigatório
    private String cargo;   // Obrigatório
    private Double salario; // Opcional

    public Funcionario() {}

    public Funcionario(String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade, String cargo, Double salario) {
        super(nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(Long idPessoa, String nomeCompleto, LocalDate dataNascimento, String documento, String pais, String estado, String cidade, Long id, String cargo, Double salario) {
        super(idPessoa, nomeCompleto, dataNascimento, documento, pais, estado, cidade);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString()
                +"\n"
                +"\tFuncionario{ "
                +"   id=" + id +
                " |     cargo='" + cargo +
                " |     salario=" + salario +
                "   }";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
