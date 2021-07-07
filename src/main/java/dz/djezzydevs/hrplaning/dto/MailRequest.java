package dz.djezzydevs.hrplaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailRequest implements Serializable {
    private String mailSubject;
    private String mailDescription;

    private String[] mails;
    private String[] cc;
}
