package co.com.reto.rabbitmq.dto;

import java.time.LocalDateTime;

public class InvocacionDTO {

    private String codigoIso;
    private LocalDateTime fechaCreacion;

    public InvocacionDTO(String codigoIso, LocalDateTime fechaCreacion) {
        this.codigoIso = codigoIso;
        this.fechaCreacion = fechaCreacion;
    }

    public InvocacionDTO() {
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
