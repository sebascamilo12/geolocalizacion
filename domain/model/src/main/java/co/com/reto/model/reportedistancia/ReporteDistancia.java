package co.com.reto.model.reportedistancia;
import lombok.Builder;

@Builder(toBuilder = true)
public class ReporteDistancia {

    private String distanciaCercana;
    private String distanciaLejana;
    private String promedio;

    public ReporteDistancia(String distanciaCercana, String distanciaLejana, String promedio) {
        this.distanciaCercana = distanciaCercana;
        this.distanciaLejana = distanciaLejana;
        this.promedio = promedio;
    }


    public ReporteDistancia() {
    }

    public void setDistanciaCercana(String distanciaCercana) {
        this.distanciaCercana = distanciaCercana;
    }

    public void setDistanciaLejana(String distanciaLejana) {
        this.distanciaLejana = distanciaLejana;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getDistanciaCercana() {
        return distanciaCercana;
    }

    public String getDistanciaLejana() {
        return distanciaLejana;
    }

    public String getPromedio() {
        return promedio;
    }
}
