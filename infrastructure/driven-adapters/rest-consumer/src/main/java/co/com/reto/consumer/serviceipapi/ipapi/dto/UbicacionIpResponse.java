package co.com.reto.consumer.serviceipapi.ipapi.dto;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UbicacionIpResponse {

    private String ip;
    private String country_name;
    private String country_code;
    private String latitude;
    private String longitude;
    private UbicacionResponse location;

}