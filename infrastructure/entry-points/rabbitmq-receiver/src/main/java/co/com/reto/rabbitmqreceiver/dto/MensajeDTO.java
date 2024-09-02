package co.com.reto.rabbitmqreceiver.dto;

public class MensajeDTO {
    private String id;
    private String mensaje;

    public MensajeDTO(String id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public MensajeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
