package co.com.reto.r2dbcrepository.moneda.model;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder(toBuilder = true)
@Table("moneda")
public class MonedaData {

    @Column("CODIGO_MONEDA")
    private String codigoMoneda;
    @Column("CODIGO_ISO")
    private String codigoIso;

    public MonedaData(String codigoMoneda, String codigoIso) {
        this.codigoMoneda = codigoMoneda;
        this.codigoIso = codigoIso;
    }

    public MonedaData() {
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }
}
