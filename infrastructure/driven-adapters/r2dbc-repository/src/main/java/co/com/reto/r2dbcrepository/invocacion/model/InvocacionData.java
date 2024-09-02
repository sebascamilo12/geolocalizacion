package co.com.reto.r2dbcrepository.invocacion.model;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Table("invocaciones")
public class InvocacionData {

    @Column("CODIGO_ISO")
    String codigoIso;
    @Column("FECHA_INVOCACION")
    LocalDateTime fechaCreacion;

    public InvocacionData(String codigoIso, LocalDateTime fechaCreacion) {
        this.codigoIso = codigoIso;
        this.fechaCreacion = fechaCreacion;
    }

    public InvocacionData() {
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
