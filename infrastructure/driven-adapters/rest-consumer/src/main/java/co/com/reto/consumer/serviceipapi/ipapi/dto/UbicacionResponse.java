package co.com.reto.consumer.serviceipapi.ipapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UbicacionResponse {

    private List<IdiomaResponse> languages;
}
