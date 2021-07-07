package dz.djezzydevs.hrplaning.services;

import dz.djezzydevs.hrplaning.dto.MailRequest;
import dz.djezzydevs.hrplaning.dto.MailResponse;

import javax.activation.DataSource;

public interface MailService {

    MailResponse<String> sendNotifications(MailRequest mailRequest);

    MailResponse<String> sendNotifications(MailRequest mailRequest, DataSource joint);
}

