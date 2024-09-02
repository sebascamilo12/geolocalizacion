package co.com.reto.model.moneda;
import lombok.Builder;


@Builder(toBuilder = true)
public class Moneda {

    private String codigoMoneda;
    private String codigoIso;

    public Moneda(String codigoMoneda, String codigoIso) {
        this.codigoMoneda = codigoMoneda;
        this.codigoIso = codigoIso;
    }

    public Moneda() {
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public String getCodigoIso() {
        return codigoIso;
    }
}
