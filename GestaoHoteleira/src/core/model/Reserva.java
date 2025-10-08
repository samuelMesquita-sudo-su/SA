package core.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {

    // Atributos
    private Long id;                    // Obrigatório
    private Cliente clienteResponsavel; // Obrigatório
    private LocalDate dataInicio;       // Obrigatório
    private LocalDate dataFim;          // Obrigatório
    private Double valorTotal;          // Opcional
    private Integer qtdHospedes;        // Obrigatório

    public Reserva(){}

    public Reserva(Cliente clienteResponsavel, LocalDate dataInicio, LocalDate dataFim, Double valorTotal, Integer qtdHospedes) {
        this.clienteResponsavel = clienteResponsavel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.qtdHospedes = qtdHospedes;
    }

    public Reserva(Long id, Cliente clienteResponsavel, LocalDate dataInicio, LocalDate dataFim, Double valorTotal, Integer qtdHospedes) {
        this.id = id;
        this.clienteResponsavel = clienteResponsavel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.qtdHospedes = qtdHospedes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getClienteResponsavel() {
        return clienteResponsavel;
    }

    public void setClienteResponsavel(Cliente clienteResponsavel) {
        this.clienteResponsavel = clienteResponsavel;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQtdHospedes() {
        return qtdHospedes;
    }

    public void setQtdHospedes(Integer qtdHospedes) {
        this.qtdHospedes = qtdHospedes;
    }

    @Override
    public String toString() {
        return "Reserva{ " +
                "   id=" + id +
                " |     clienteResponsavel=" + clienteResponsavel +
                " |     dataInicio=" + dataInicio +
                " |     dataFim=" + dataFim +
                " |     valorTotal=" + valorTotal +
                " |     qtdHospedes=" + qtdHospedes +
                "   }";
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
