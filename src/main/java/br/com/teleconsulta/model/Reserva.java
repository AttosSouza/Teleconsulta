package br.com.teleconsulta.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_termino", nullable = false)
    private LocalDateTime dataHoraTermino;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "usuario_cancelamento_id")
    private Usuario usuarioCancelamento;

    @Column(name = "motivo_cancelamento", length = 255)
    private String motivoCancelamento;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(LocalDateTime dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Usuario getUsuarioCancelamento() {
        return usuarioCancelamento;
    }

    public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
        this.usuarioCancelamento = usuarioCancelamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
