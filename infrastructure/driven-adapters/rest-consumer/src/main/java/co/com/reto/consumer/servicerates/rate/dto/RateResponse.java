package co.com.reto.consumer.servicerates.rate.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RateResponse {

    private Map<String, Double> rates;
}
