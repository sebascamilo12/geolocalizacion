package co.com.reto.r2dbcrepository.distancia.model;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder(toBuilder = true)
@Table("distancia")
public class DistanciaData {

    @Column("CODIGO_ISO")
    private String codigoIso;
    @Column("DISTANCIA_A_BUENOSAIRES")
    private Double distanciaABuenosAires;

    public DistanciaData(String codigoIso, Double distanciaABuenosAires) {
        this.codigoIso = codigoIso;
        this.distanciaABuenosAires = distanciaABuenosAires;
    }

    public DistanciaData() {
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public Double getDistanciaABuenosAires() {
        return distanciaABuenosAires;
    }

    public void setDistanciaABuenosAires(Double distanciaABuenosAires) {
        this.distanciaABuenosAires = distanciaABuenosAires;
    }
}
