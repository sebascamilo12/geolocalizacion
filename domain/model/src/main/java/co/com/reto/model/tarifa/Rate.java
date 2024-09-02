package co.com.reto.model.tarifa;

import lombok.Builder;

import java.util.Map;

@Builder(toBuilder = true)
public class Rate {

    private Map<String, Double> rates;

    public Rate(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Rate() {
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
