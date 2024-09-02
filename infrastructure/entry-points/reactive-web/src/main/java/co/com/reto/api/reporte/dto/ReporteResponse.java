package co.com.reto.api.reporte.dto;

public class ReporteResponse {

    private String distanciaCercana;
    private String distanciaLejana;
    private String promedio;

    public ReporteResponse(String distanciaCercana, String distanciaLejana, String promedio) {
        this.distanciaCercana = distanciaCercana;
        this.distanciaLejana = distanciaLejana;
        this.promedio = promedio;
    }

    public ReporteResponse() {
    }

    public String getDistanciaCercana() {
        return distanciaCercana;
    }

    public void setDistanciaCercana(String distanciaCercana) {
        this.distanciaCercana = distanciaCercana;
    }

    public String getDistanciaLejana() {
        return distanciaLejana;
    }

    public void setDistanciaLejana(String distanciaLejana) {
        this.distanciaLejana = distanciaLejana;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
}
