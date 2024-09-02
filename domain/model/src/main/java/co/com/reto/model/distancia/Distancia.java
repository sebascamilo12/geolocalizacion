package co.com.reto.model.distancia;
import lombok.Builder;


@Builder(toBuilder = true)
public class Distancia {

    private String codigoIso;
    private Double distanciaABuenosAires;

    public Distancia(String codigoIso, Double distanciaABuenosAires) {
        this.codigoIso = codigoIso;
        this.distanciaABuenosAires = distanciaABuenosAires;
    }

    public Distancia() {
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public void setDistanciaABuenosAires(Double distanciaABuenosAires) {
        this.distanciaABuenosAires = distanciaABuenosAires;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public Double getDistanciaABuenosAires() {
        return distanciaABuenosAires;
    }
}
