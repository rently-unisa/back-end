package it.unisa.c02.rently.rently_application.commons.services.noleggioService;

import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;


/**
 * Implementazione del servizio di eliminazione o cambio di stato di un nolegggio.
 *
 */
@Service
@RequiredArgsConstructor
public class NoleggioService {

    /**
     * Istanza di GestioneNoleggioDAO utilizzata per l'accesso ai dati dei noleggi.
     */
    private final GestioneNoleggioService noleggioService;


    /**
     * Funzione che aggiorna lo stato di un noleggio da 'IN_CORSO' a 'FINE' qunado la
     * data di fine di un noleggio coincide con la data odierna
     */
    @Scheduled(fixedDelay = 30000)
    public void updateFineNoleggio() throws InterruptedException {
        try {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            List<Noleggio> list = noleggioService.checkFineNoleggio(date);

            for(Noleggio item:list) {
                item.setStato(Noleggio.EnumStato.FINE);
                noleggioService.updateStatoNoleggio(item);
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Funzione che elimina tutti i noleggi in stato 'RICHIESTA' e 'RIFIUTATA' dopo che è passata una settimana
     * dalla data della richiesta di noleggio
     */
    @Scheduled(fixedDelay = 100000)
    public void checkRichiestaNoleggio() throws InterruptedException {
        try {

            List<Noleggio> list = noleggioService.findRichieste();
            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            for(Noleggio item:list) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(item.getDataRichiesta());
                cal.add(Calendar.DATE, 7);
                java.sql.Date dataRichiestaModifica = new java.sql.Date(cal.getTime().getTime());

                if(dataRichiestaModifica.toString().equals(today.toString())){

                    noleggioService.deleteNoleggio(item);

                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}