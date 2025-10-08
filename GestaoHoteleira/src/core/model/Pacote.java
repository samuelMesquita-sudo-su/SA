package core.model;

import java.util.Objects;

public class Pacote {

    // Atributos
    private Long id;                // Obrigatório
    private Acomodacao acomodacao;  // Obrigatório
    private String nome;            // Obrigatório
    private Integer qtdDiarias;     // Obrigatório
    private Double valorTotal;      // Opcional

    public Pacote(){}

    public Pacote(Acomodacao acomodacao, String nome, Integer qtdDiarias, Double valorTotal) {
        this.acomodacao = acomodacao;
        this.nome = nome;
        this.qtdDiarias = qtdDiarias;
        this.valorTotal = valorTotal;
    }

    public Pacote(Long id, Acomodacao acomodacao, String nome, Integer qtdDiarias, Double valorTotal) {
        this.id = id;
        this.acomodacao = acomodacao;
        this.nome = nome;
        this.qtdDiarias = qtdDiarias;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Pacote{ " +
                "   id=" + id +
                " |     acomodacao=" + acomodacao +
                " |     nome='" + nome + '\'' +
                " |     qtdDiarias=" + qtdDiarias +
                " |     valorTotal=" + valorTotal +
                "   }";
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
