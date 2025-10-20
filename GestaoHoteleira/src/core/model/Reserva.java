package core.model;

import base.util.Utilidades;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {

    // Atributos
    private Long id; // Identificador
    private LocalDate dataInicio; // Obrigatório
    private LocalDate dataFim; // Obrigatório
    private Acomodacao acomodacao; // Obrigatório
    private Cliente clienteResponsavel; // Obrigatório
    private Integer qtdHospedes; // Obrigatório
    private Double valorTotal; // Opcional

    // Construtor vazio
    public Reserva() {}

    // Construtor sem o id
    public Reserva(
        LocalDate dataInicio,
        LocalDate dataFim,
        Acomodacao acomodacao,
        Cliente clienteResponsavel,
        Integer qtdHospedes,
        Double valorTotal
    ) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.acomodacao = acomodacao;
        this.clienteResponsavel = clienteResponsavel;
        this.qtdHospedes = qtdHospedes;
        this.valorTotal = valorTotal;
    }

    // Construtor com todos os atributos
    public Reserva(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        Acomodacao acomodacao,
        Cliente clienteResponsavel,
        Integer qtdHospedes,
        Double valorTotal
    ) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.acomodacao = acomodacao;
        this.clienteResponsavel = clienteResponsavel;
        this.qtdHospedes = qtdHospedes;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public Cliente getClienteResponsavel() {
        return clienteResponsavel;
    }

    public void setClienteResponsavel(Cliente clienteResponsavel) {
        this.clienteResponsavel = clienteResponsavel;
    }

    public Integer getQtdHospedes() {
        return qtdHospedes;
    }

    public void setQtdHospedes(Integer qtdHospedes) {
        this.qtdHospedes = qtdHospedes;
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
            +  " | Data início: " + Utilidades.formatarDataBR(dataInicio)
            +  " | Data fim: " + Utilidades.formatarDataBR(dataFim)
            +  " | Acomodação: " + acomodacao.getId() + " - " + acomodacao.getNome()
            +  " | Cliente responsável: " + clienteResponsavel.getId() + " - " + clienteResponsavel.getNomeCompleto()
            +  " | Quantidade de hóspedes: " + qtdHospedes
            +  " | Valor total: " + Utilidades.formatarValorMonetario(valorTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
