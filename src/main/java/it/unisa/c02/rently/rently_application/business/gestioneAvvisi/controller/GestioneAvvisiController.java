package it.unisa.c02.rently.rently_application.business.gestioneAvvisi.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.business.gestioneAvvisi.service.GestioneAvvisiService;
import it.unisa.c02.rently.rently_application.commons.mail.EmailService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.data.dto.SegnalazioneDTO;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avvisi")
public class GestioneAvvisiController {

    private final GestioneAvvisiService avvisiService;
    private final GestioneAreaPersonaleService AreaPersonaleService;
    private final ResponseService responseService;
    private final EmailService emailService;
    private final GestioneAnnuncioService annuncioService;

    @PostMapping("/aggiungi-segnalazione")
    public ResponseEntity<String> aggiungiSegnalazione(@RequestBody SegnalazioneDTO segnalazioneDTO) {

        try {
            //prendo l'utente dalla sessione con @AuthenticationPrincipal UserDetails userDetails
            //controllo utente sessione
            Segnalazione segnalazione = new Segnalazione();
            segnalazione.setContenuto(segnalazioneDTO.getContenuto());
            segnalazione.setTipo(Segnalazione.EnumTipo.valueOf(segnalazioneDTO.getTipo()));
            Utente user = AreaPersonaleService.getDatiPrivati(segnalazione.getId());
            if (user != null) {
                segnalazione.setSegnalatore(user);
            }
            Segnalazione nuova = avvisiService.addSegnalazione(segnalazione);

            if (nuova.getSegnalatore() != null) {
                return responseService.Ok(nuova);
            } else
                return responseService.InternalError();

        } catch (Exception e) {
            return responseService.InternalError();
        }
    }

    @GetMapping("/notifica-arrivo-richiesta-noleggio")
    public ResponseEntity<String> notificaArrivoRichiestaNoleggio(@RequestBody Noleggio noleggio){
        try{
            Utente noleggiatore= noleggio.getNoleggiatore();
            Annuncio annuncio = annuncioService.getAnnuncio(noleggio.getAnnuncio().getId()).orElse(null);
            if(noleggiatore!= null && annuncio!=null){
                String subject = "Nuova richiesta di noleggio";
                String text = "Salve "+noleggiatore.getUsername()+",\nhai appena ricevuto una nuova richiesta di noleggio da parte di "+noleggio.getNoleggiante().getUsername()+" per"+
                        "il tuo annuncio "+annuncio.getNome()+".\nAccedi a Rently e controlla le tue richieste per saperne di più!";
                emailService.sendEmail(noleggiatore.getEmail(), subject,text);
                return responseService.Ok();
            }
            return responseService.InternalError();
        }catch(Exception e){
            return responseService.InternalError();
        }
    }

    @GetMapping("/notifica-richiesta-noleggio-accettata")
    public ResponseEntity<String> notificaRichiestaNoleggioAccettata(@RequestBody Noleggio noleggio){
        try{
            Utente noleggiante= noleggio.getNoleggiante();
            Annuncio annuncio = annuncioService.getAnnuncio(noleggio.getAnnuncio().getId()).orElse(null);
            if(noleggiante!= null && annuncio!=null){
                String subject = "Richiesta di noleggio accettata";
                String text = "Salve "+noleggiante.getUsername()+",\nla tua richiesta di noloeggio per l'annuncio "+annuncio.getNome()+" è stata appena accettata!." +
                        "\nAccedi a Rently per procedere con il pagamento e dare inizio al periodo di noleggio!";

                emailService.sendEmail(noleggiante.getEmail(), subject,text);
                return responseService.Ok();
            }
            return responseService.InternalError();
        }catch(Exception e){
            return responseService.InternalError();
        }
    }

    @GetMapping("/notifica-richiesta-noleggio-rifiutata")
    public ResponseEntity<String> notificaRichiestaNoleggioRifiutata(@RequestBody Noleggio noleggio){
        try{
            Utente noleggiante= noleggio.getNoleggiante();
            Annuncio annuncio = annuncioService.getAnnuncio(noleggio.getAnnuncio().getId()).orElse(null);
            if(noleggiante!= null && annuncio!=null){
                String subject = "Richiesta di noleggio accettata";
                String text = "Salve "+noleggiante.getUsername()+",\nla tua richiesta di noloeggio per l'annuncio "+annuncio.getNome()+" è stata rifiutata." +
                        "\nAccedi a Rently e controlla le tue richieste per saperne di più.";

                emailService.sendEmail(noleggiante.getEmail(), subject,text);
                return responseService.Ok();
            }
            return responseService.InternalError();
        }catch(Exception e){
            return responseService.InternalError();
        }
    }

    @GetMapping("/notifica-inizio-noleggio")
    public ResponseEntity<String> notificaInizioNoleggio(@RequestBody Noleggio noleggio){
        try{
            Utente noleggiante= noleggio.getNoleggiante();
            Utente noleggiatore= noleggio.getNoleggiatore();
            Annuncio annuncio = annuncioService.getAnnuncio(noleggio.getAnnuncio().getId()).orElse(null);
            if(noleggiante!= null && noleggiatore!= null && annuncio!=null){
                String subject = "Inizio periodo di noleggio";
                String textNoleggiante = "Salve "+noleggiante.getUsername()+",\nil periodo di noleggio per l'oggetto "+annuncio.getNome()+" è appena iniziato." +
                        "\nRicordiamo che la scadenza del periodo è prevista per il giorno "+noleggio.getDataFine()+". Accedi a Rently e controlla i tuoi noleggi per saperne di più.";
                String textNoleggiatore = "Salve "+noleggiatore.getUsername()+",\nil periodo di noleggio per l'oggetto "+annuncio.getNome()+" richiesto da "+noleggiante.getUsername()+" è appena iniziato." +
                        "\nRicordiamo che la scadenza del periodo è prevista per il giorno "+noleggio.getDataFine()+". Accedi a Rently e controlla i tuoi noleggi per saperne di più.";

                emailService.sendEmail(noleggiante.getEmail(), subject,textNoleggiante);
                emailService.sendEmail(noleggiatore.getEmail(), subject,textNoleggiatore);
                return responseService.Ok();
            }
            return responseService.InternalError();
        }catch(Exception e){
            return responseService.InternalError();
        }
    }

    @GetMapping("/notifica-fine-noleggio")
    public ResponseEntity<String> notificaFineNoleggio(@RequestBody Noleggio noleggio){
        try{
            Utente noleggiante= noleggio.getNoleggiante();
            Utente noleggiatore= noleggio.getNoleggiatore();
            Annuncio annuncio = annuncioService.getAnnuncio(noleggio.getAnnuncio().getId()).orElse(null);
            if(noleggiante!= null && noleggiatore!= null && annuncio!=null){
                String subject = "Fine periodo di noleggio";
                String textNoleggiante = "Salve "+noleggiante.getUsername()+",\nil periodo di noleggio per l'oggetto "+annuncio.getNome()+" è terminato." +
                        "\nTi invitiamo a condividere la tua esperienza lasciando una valutazione sul proprietario e sull'oggetto noleggiato. Accedi a Rently e controlla i tuoi noleggi per saperne di più.";
                String textNoleggiatore = "Salve "+noleggiatore.getUsername()+",\nil periodo di noleggio per l'oggetto "+annuncio.getNome()+" richiesto da "+noleggiante.getUsername()+" è terminato." +
                        "\nTi invitiamo a condividere la tua esperienza lasciando una valutazione su "+noleggiante.getUsername()+". Accedi a Rently e controlla i tuoi noleggi per saperne di più.";

                emailService.sendEmail(noleggiante.getEmail(), subject,textNoleggiante);
                emailService.sendEmail(noleggiatore.getEmail(), subject,textNoleggiatore);
                return responseService.Ok();
            }
            return responseService.InternalError();
        }catch(Exception e){
            return responseService.InternalError();
        }
    }

}
