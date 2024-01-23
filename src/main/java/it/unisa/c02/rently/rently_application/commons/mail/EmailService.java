package it.unisa.c02.rently.rently_application.commons.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementazione del servizio di invio notifiche tramite email.
 *
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    /**
     * Istanza di JavaMailSender utilizzata per l'invio delle mail.
     */
    private final JavaMailSender emailSender;

    /**
     * Invia un mail con oggetto 'subject' contenente 'text' a 'to'.
     *
     * @param subject l'oggetto della mail.
     * @param text il contenuto della mail.
     * @param to il destinatario della mail.
     *           
     */
    public void sendEmail(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
