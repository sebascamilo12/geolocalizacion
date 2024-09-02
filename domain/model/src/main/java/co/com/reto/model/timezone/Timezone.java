package co.com.reto.model.timezone;
import lombok.Builder;


import java.util.List;


@Builder(toBuilder = true)
public class Timezone {

    private List<String> timezones;

    public Timezone(List<String> timezones) {
        this.timezones = timezones;
    }

    public Timezone() {
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getTimezones() {
        return timezones;
    }
}
