package core.model;

import java.util.Objects;

public class Acomodacao {

    // Atributos
    private Long id;                                // Obrigatório
    private String nome;                            // Obrigatíorio
    private Double valorDiaria;                     // Obrigatíorio
    private Integer limiteHospedes;                 // Obrigatíorio
    private String descricao;                       // Opcional
    private Funcionario funcionarioResponsavel;     // Obrigatório

    public Acomodacao(){}

    public Acomodacao(String nome, Double valorDiaria, Integer limiteHospedes, String descricao, Funcionario funcionarioResponsavel) {
        this.nome = nome;
        this.valorDiaria = valorDiaria;
        this.limiteHospedes = limiteHospedes;
        this.descricao = descricao;
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public Acomodacao(Long id, String nome, Double valorDiaria, Integer limiteHospedes, String descricao, Funcionario funcionarioResponsavel) {
        this.id = id;
        this.nome = nome;
        this.valorDiaria = valorDiaria;
        this.limiteHospedes = limiteHospedes;
        this.descricao = descricao;
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

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

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Integer getLimiteHospedes() {
        return limiteHospedes;
    }

    public void setLimiteHospedes(Integer limiteHospedes) {
        this.limiteHospedes = limiteHospedes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    @Override
    public String toString() {
        return "Acomodacao{ " +
                "   id=" + id +
                " |     nome='" + nome + '\'' +
                " |     valorDiaria=" + valorDiaria +
                " |     limiteHospedes=" + limiteHospedes +
                " |     descricao='" + descricao + '\'' +
                " |     funcionarioResponsavel=" + funcionarioResponsavel +
                "   }";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Acomodacao that = (Acomodacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
