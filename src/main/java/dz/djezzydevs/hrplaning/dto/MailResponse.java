package dz.djezzydevs.hrplaning.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailResponse<T> implements Serializable {

    private final T body;
    private boolean success;
    private String message;

    public MailResponse(T body) {
        this.body = body;
        success = true;
        this.message = "Opération effectuée";
    }

    public MailResponse(String message) {
        this.success = false;
        this.body = null;
        this.message = message;
    }

    public MailResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.body = null;
    }

    public MailResponse(T body, boolean success, String message) {
        this.body = body;
        this.success = success;
        this.message = message;
    }
}
