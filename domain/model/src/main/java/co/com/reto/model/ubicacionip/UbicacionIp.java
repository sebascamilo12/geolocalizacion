package co.com.reto.model.ubicacionip;

import lombok.Builder;

import java.util.List;


@Builder(toBuilder = true)
public class UbicacionIp {

    private String ip;
    private String pais;
    private String codigoISO;
    private Double latitud;
    private Double longitud;
    private List<String> idiomas;
    private String moneda;
    private String distanciaEstimada;
    private List<String> horarios;

    public UbicacionIp(String ip, String pais, String codigoISO, Double latitud, Double longitud, List<String> idiomas, String moneda, String distanciaEstimada, List<String> horarios) {
        this.ip = ip;
        this.pais = pais;
        this.codigoISO = codigoISO;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idiomas = idiomas;
        this.moneda = moneda;
        this.distanciaEstimada = distanciaEstimada;
        this.horarios = horarios;
    }

    public UbicacionIp() {
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setDistanciaEstimada(String distanciaEstimada) {
        this.distanciaEstimada = distanciaEstimada;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public String getIp() {
        return ip;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getDistanciaEstimada() {
        return distanciaEstimada;
    }

    public List<String> getHorarios() {
        return horarios;
    }
}
