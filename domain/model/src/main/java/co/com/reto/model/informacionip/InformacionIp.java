package co.com.reto.model.informacionip;
import lombok.Builder;

import java.util.Date;


@Builder(toBuilder = true)
public class InformacionIp {
    private String ip;
    private Date fechaActual;
    private String pais;
    private String codigoISO;
    private String idioma;
    private String hora;
    private String monedaLocal;
    private String distanciaEstima;

    public InformacionIp(String ip, Date fechaActual, String pais, String codigoISO, String idioma, String hora, String monedaLocal, String distanciaEstima) {
        this.ip = ip;
        this.fechaActual = fechaActual;
        this.pais = pais;
        this.codigoISO = codigoISO;
        this.idioma = idioma;
        this.hora = hora;
        this.monedaLocal = monedaLocal;
        this.distanciaEstima = distanciaEstima;
    }

    public InformacionIp() {
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setMonedaLocal(String monedaLocal) {
        this.monedaLocal = monedaLocal;
    }

    public void setDistanciaEstima(String distanciaEstima) {
        this.distanciaEstima = distanciaEstima;
    }

    public String getIp() {
        return ip;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getHora() {
        return hora;
    }

    public String getMonedaLocal() {
        return monedaLocal;
    }

    public String getDistanciaEstima() {
        return distanciaEstima;
    }
}
