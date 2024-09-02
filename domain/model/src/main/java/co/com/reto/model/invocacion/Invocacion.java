package co.com.reto.model.invocacion;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder(toBuilder = true)
public class Invocacion {

    private String codigoIso;
    private LocalDateTime fechaCreacion;

    public Invocacion(String codigoIso, LocalDateTime fechaCreacion) {
        this.codigoIso = codigoIso;
        this.fechaCreacion = fechaCreacion;
    }

    public Invocacion() {
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
