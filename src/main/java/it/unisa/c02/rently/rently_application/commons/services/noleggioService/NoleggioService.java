package it.unisa.c02.rently.rently_application.commons.services.noleggioService;

import it.unisa.c02.rently.rently_application.business.gestioneNoleggio.service.GestioneNoleggioService;
import it.unisa.c02.rently.rently_application.data.model.Noleggio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoleggioService {

    private final GestioneNoleggioService noleggioService;

    @Scheduled(fixedDelay = 1000)
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
            ;
        }
    }

}