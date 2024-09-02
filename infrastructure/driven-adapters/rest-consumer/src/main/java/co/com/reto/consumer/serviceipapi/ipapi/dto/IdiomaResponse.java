package co.com.reto.consumer.serviceipapi.ipapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IdiomaResponse {
    private String code;
    private String name;
}
