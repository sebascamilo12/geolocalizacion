package co.com.reto.consumer.serviceapitimezone.timezone.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TimeZoneResponse {

    private List<String> timezones;
}
