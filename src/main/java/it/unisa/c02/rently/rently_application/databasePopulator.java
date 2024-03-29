package it.unisa.c02.rently.rently_application;

import it.unisa.c02.rently.rently_application.business.gestioneChat.service.GestioneChatService;
import it.unisa.c02.rently.rently_application.commons.psw.PswCoder;
import it.unisa.c02.rently.rently_application.data.dao.*;
import it.unisa.c02.rently.rently_application.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class databasePopulator implements CommandLineRunner {

    private final GestioneAnnuncioDAO annuncioService;
    private final GestioneValutazioneUtenteDAO valutazioneUtenteService;
    private final GestioneValutazioneOggettoDAO valutazioneOggettoService;
    private final GestioneNoleggioDAO noleggioService;
    private final GestioneAutenticazioneDAO utenteService;
    private final PswCoder pswCoder;
    private final GestioneChatDAO chatService;



    @Override
    public void run(String... strings) throws Exception {

        // Aggiunta degli Utenti


        Utente user1 =new Utente(1,"user1", "Mario", "Rossi", "mario.rossi@email.com", pswCoder.codificaPassword("password123"), false);
        Utente user2 =new Utente(2,"user2", "Luca", "Bianchi", "luca.bianchi@email.com", pswCoder.codificaPassword("securePass"), false);
        Utente user3 =new Utente(3,"user3", "Laura", "Verdi", "laura.verdi@email.com", pswCoder.codificaPassword("pass123"), false);
        Utente user4 =new Utente(4,"user4", "Alessia", "Neri", "alessia.neri@email.com", pswCoder.codificaPassword("alessia_pass"), false);
        Utente user5 =new Utente(5,"user5", "Giovanni", "Rosa", "giovanni.rosa@email.com", pswCoder.codificaPassword("rosa123"), false);
        Utente user6 =new Utente(6,"user6", "Francesca", "Giallo", "francesca.giallo@email.com", pswCoder.codificaPassword("fran_pass"), false);
        Utente user7 =new Utente(7,"user7", "Marco", "Arancio", "marco.arancio@email.com", pswCoder.codificaPassword("marco_pass"), false);
        Utente user8 =new Utente(8,"user8", "Elena", "Blu", "elena.blu@email.com", pswCoder.codificaPassword("elena_secure"), false);
        Utente user9 =new Utente(9,"user9", "Davide", "Marrone", "davide.marrone@email.com", pswCoder.codificaPassword("marrone_pass"), false);
        Utente user10 =new Utente(10,"user10", "Giulia", "Rosa", "giulia.rosa@email.com", pswCoder.codificaPassword("giulia_secure"), false);
        Utente user11 =new Utente(11,"user11", "Riccardo", "Azzurro", "riccardo.azzurro@email.com", pswCoder.codificaPassword("azzurro_pass"), false);
        Utente user12 =new Utente(12,"user12", "Cristina", "Verde", "cristina.verde@email.com", pswCoder.codificaPassword("cristina123"), false);
        Utente user13 =new Utente(13,"user13", "Paolo", "Viola", "paolo.viola@email.com", pswCoder.codificaPassword("paolo_pass"), true);
        Utente user14 =new Utente(14,"user14", "Simona", "Rosa", "simona.rosa@email.com", pswCoder.codificaPassword("simona_secure"), true);
        Utente user15 =new Utente(15,"user15", "Federico", "Giallo", "federico.giallo@email.com", pswCoder.codificaPassword("fede_pass"), true);

        this.utenteService.save(user1);
        this.utenteService.save(user2);
        this.utenteService.save(user3);
        this.utenteService.save(user4);
        this.utenteService.save(user5);
        this.utenteService.save(user6);
        this.utenteService.save(user7);
        this.utenteService.save(user8);
        this.utenteService.save(user9);
        this.utenteService.save(user10);
        this.utenteService.save(user11);
        this.utenteService.save(user12);
        this.utenteService.save(user13);
        this.utenteService.save(user14);
        this.utenteService.save(user15);


        // Aggiunta degli Annunci

        Annuncio annuncio1 = new Annuncio(1,"Smartphone Samsung", "Via Roma", "Milan", "97534", "Telefono in ottime condizioni", new BigDecimal("30.00"), "img1.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-05"), user2, null, null);
        Annuncio annuncio2 = new Annuncio(2,"Libro di Fantascienza", "Corso Magenta", "Torin", "10123", "Romanzo di Isaac Asimov", new BigDecimal("5.00"), "img2.jpg", Annuncio.EnumCategoria.LIBRI, Annuncio.EnumCondizione.BUONA, Date.valueOf("2024-11-15"), user2, null, null);
        Annuncio annuncio3 = new Annuncio(3,"Lavatrice Whirlpool", "Via Garibaldi", "Roma", "00185", "Lavatrice con centrifuga", new BigDecimal("100.00"), "img3.jpg", Annuncio.EnumCategoria.ELETTRODOMESTICI, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2025-01-20"), user4, null, null);
        Annuncio annuncio4 = new Annuncio(4,"Tagliaerba Bosch", "Via Veneto", "Diamante", "80122", "Tagliaerba elettrico", new BigDecimal("60.00"), "img4.jpg", Annuncio.EnumCategoria.GIARDINO, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-12-10"), user8, null, null);
        Annuncio annuncio5 = new Annuncio(5,"Attrezzi da giardinaggio", "Via Dante", "Firenze", "50123", "Kit completo per giardinaggio", new BigDecimal("52.00"), "img5.jpg", Annuncio.EnumCategoria.GIARDINAGGIO, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-03-07"), user6, null, null);
        Annuncio annuncio6 = new Annuncio(6,"Quadro moderno", "Via dei Fiori", "Bologna", "40121", "Dipinto astratto su tela", new BigDecimal("65.00"), "img6.jpg", Annuncio.EnumCategoria.ARTE, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2024-10-25"), user8, null, null);
        Annuncio annuncio7 = new Annuncio(7,"Chitarra acustica", "Via 14 Settembre", "Arezzo", "15121", "Chitarra acustica Fender", new BigDecimal("77.00"), "img7.jpg", Annuncio.EnumCategoria.MUSICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-11-30"), user7, null, null);
        Annuncio annuncio8 = new Annuncio(8,"Tostapane Bosch", "Via Vittorio Emanuele", "Palermo", "90123", "Tostapane a 4 fette", new BigDecimal("10.00"), "img8.jpg", Annuncio.EnumCategoria.CASAECUCINA, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-02-15"), user12, null, null);
        Annuncio annuncio9 = new Annuncio(9,"Tapis roulant elettrico", "Via Gambardella", "Catania", "95121", "Tapis roulant, pieghevole con Altoparlante Bluetooth Integrato", new BigDecimal("47.00"), "img9.jpg", Annuncio.EnumCategoria.CASAECUCINA, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-05-12"), user13, null, null);
        Annuncio annuncio10 = new Annuncio(10,"Computer portatile HP", "Corso Umberto", "Verona", "37123", "Notebook HP con processore i5", new BigDecimal("43.00"), "img10.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-08-03"), user1, null, null);
        Annuncio annuncio11 = new Annuncio(11,"Tavolo in legno", "Via della Moscova", "Milano", "20121", "Tavolo rettangolare in legno massiccio", new BigDecimal("50.00"), "img11.jpg", Annuncio.EnumCategoria.CASAECUCINA, Annuncio.EnumCondizione.BUONA, Date.valueOf("2024-12-20"), user11, null, null);
        Annuncio annuncio12 = new Annuncio(12,"Scarpe da running", "Via Toledo", "Napoli", "80121", "Scarpe Nike da running, numero 42", new BigDecimal("7.00"), "img12.jpg", Annuncio.EnumCategoria.SPORT, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-03-25"), user12, null, null);
        Annuncio annuncio13 = new Annuncio(13,"Fotocamera Canon", "Corso Italia", "Roma", "00192", "Canon EOS 80D con obiettivo", new BigDecimal("95.00"), "img13.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2025-01-10"), user13, null, null);
        Annuncio annuncio14 = new Annuncio(14,"Pentola a pressione", "Via Po", "Torino", "10163", "Pentola a pressione in acciaio", new BigDecimal("15.00"), "img14.jpg", Annuncio.EnumCategoria.CASAECUCINA, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-04-18"), user14, null, null);
        Annuncio annuncio15 = new Annuncio(15,"Bicicletta da corsa", "Corso Garibaldi", "Bologna", "40122", "Bici da corsa Bianchi, taglia L", new BigDecimal("120.00"), "img15.jpg", Annuncio.EnumCategoria.SPORT, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-07-11"), user15, null, null);
        Annuncio annuncio16 = new Annuncio(16,"Televisore Samsung", "Via XX Settembre", "Genova", "16121", "TV LED 55 pollici", new BigDecimal("200.00"), "img16.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2024-11-05"), user1, null, null);
        Annuncio annuncio17 = new Annuncio(17,"Tavola da surf", "Via Roma", "Cosenza", "90122", "Tavola da surf con accessori", new BigDecimal("150.00"), "img17.jpg", Annuncio.EnumCategoria.SPORT, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-02-20"), user2, null, null);
        Annuncio annuncio18 = new Annuncio(18,"Robot da cucina", "Corso Vittorio Emanuele", "Sassari", "95121", "Robot da cucina multifunzione", new BigDecimal("90.00"), "img18.jpg", Annuncio.EnumCategoria.CASAECUCINA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-15"), user3, null, null);
        Annuncio annuncio19 = new Annuncio(19,"Chitarra elettrica", "Via dei Mille", "Verona", "37123", "Chitarra elettrica Gibson", new BigDecimal("170.00"), "img19.jpg", Annuncio.EnumCategoria.MUSICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-10-19"), user4, null, null);
        Annuncio annuncio20 = new Annuncio(20,"About Love", "Via Fusco", "Salerno", "43765", "Libro di storie brevi scritto da Anton Chekhov", new BigDecimal("7.00"), "img20.jpg", Annuncio.EnumCategoria.LIBRI, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-06-19"), user5, null, null);
        Annuncio annuncio21 = new Annuncio(21,"MacBook Pro", "Via della Moscova", "Milano", "20821", "MacBook Pro 13 pollici, 2020", new BigDecimal("185.00"), "img21.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-01-05"), user6, null, null);
        Annuncio annuncio22 = new Annuncio(22,"Giacca nera da ufficio", "Corso Magenta", "Torino", "10123", "Giacca nera da ufficio nera, taglia M da donna", new BigDecimal("10.00"), "img22.jpg", Annuncio.EnumCategoria.OGGETTISTICAPROFESSIONALE, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-04-15"), user7, null, null);
        Annuncio annuncio23 = new Annuncio(23,"Forno a microonde", "Via Garibaldi", "Roma", "00185", "Forno a microonde Samsung", new BigDecimal("20.00"), "img23.jpg", Annuncio.EnumCategoria.ELETTRODOMESTICI, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2024-12-20"), user8, null, null);
        Annuncio annuncio24 = new Annuncio(24,"Cassetta degli attrezzi", "Via Veneto", "Catania", "80122", "Cassetta per gli attrezzi vuota della DEWALT", new BigDecimal("20.00"), "img24.jpg", Annuncio.EnumCategoria.OGGETTISTICAPROFESSIONALE, Annuncio.EnumCondizione.BUONA, Date.valueOf("2025-03-01"), user9, null, null);
        Annuncio annuncio25 = new Annuncio(25,"Tavolo da ping pong", "Via Dante", "Firenze", "50123", "Tavolo da ping pong con accessori", new BigDecimal("60.00"), "img25.jpg", Annuncio.EnumCategoria.SPORT, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2025-06-10"), user10, null, null);
        Annuncio annuncio26 = new Annuncio(26,"Lettore MP3", "Via dei Mille", "Venezia", "40121", "Lettore MP4 Sony", new BigDecimal("7.00"), "img26.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.BUONA, Date.valueOf("2024-11-30"), user11, null, null);
        Annuncio annuncio27 = new Annuncio(27,"Tappeto persiano", "Corso Vittorio Emanuele", "Palermo", "90723", "Tappeto persiano fatto a mano", new BigDecimal("45.00"), "img27.jpg", Annuncio.EnumCategoria.ARTE, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2025-02-28"), user12, null, null);
        Annuncio annuncio28 = new Annuncio(28,"Smartwatch Garmin", "Via XX Settembre", "Genova", "16141", "Smartwatch Garmin Venu", new BigDecimal("48.00"), "img28.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-10-15"), user13, null, null);
        Annuncio annuncio29 = new Annuncio(29,"Cuffie senza fili", "Corso Italia", "Roma", "00192", "Cuffie Sony WH-1000XM4", new BigDecimal("30.00"), "img29.jpg", Annuncio.EnumCategoria.ELETTRONICA, Annuncio.EnumCondizione.DISCRETA, Date.valueOf("2025-05-20"), user14, null, null);
        Annuncio annuncio30 = new Annuncio(30,"Bicicletta elettrica", "Via Toledo", "Napoli", "80121", "Bicicletta elettrica Ancheer", new BigDecimal("120.00"), "img30.jpg", Annuncio.EnumCategoria.SPORT, Annuncio.EnumCondizione.OTTIMA, Date.valueOf("2024-12-05"), user15, null, null);

        this.annuncioService.save(annuncio1);
        this.annuncioService.save(annuncio2);
        this.annuncioService.save(annuncio3);
        this.annuncioService.save(annuncio4);
        this.annuncioService.save(annuncio5);
        this.annuncioService.save(annuncio6);
        this.annuncioService.save(annuncio7);
        this.annuncioService.save(annuncio8);
        this.annuncioService.save(annuncio9);
        this.annuncioService.save(annuncio10);
        this.annuncioService.save(annuncio11);
        this.annuncioService.save(annuncio12);
        this.annuncioService.save(annuncio13);
        this.annuncioService.save(annuncio14);
        this.annuncioService.save(annuncio15);
        this.annuncioService.save(annuncio16);
        this.annuncioService.save(annuncio17);
        this.annuncioService.save(annuncio18);
        this.annuncioService.save(annuncio19);
        this.annuncioService.save(annuncio20);
        this.annuncioService.save(annuncio21);
        this.annuncioService.save(annuncio22);
        this.annuncioService.save(annuncio23);
        this.annuncioService.save(annuncio24);
        this.annuncioService.save(annuncio25);
        this.annuncioService.save(annuncio26);
        this.annuncioService.save(annuncio27);
        this.annuncioService.save(annuncio28);
        this.annuncioService.save(annuncio29);
        this.annuncioService.save(annuncio30);


        // Aggiunta Noleggio

        Noleggio noleggio1 = new Noleggio(1,Noleggio.EnumStato.CONCLUSO, new BigDecimal("100.00"), Date.valueOf("2024-02-01"), Date.valueOf("2024-02-07"), Date.valueOf("2024-01-28"), user1, user2, annuncio1);
        Noleggio noleggio2 = new Noleggio(2,Noleggio.EnumStato.CONCLUSO, new BigDecimal("200.00"), Date.valueOf("2024-02-10"), Date.valueOf("2024-02-15"), Date.valueOf("2024-02-07"), user3, user2, annuncio2);
        Noleggio noleggio3 = new Noleggio(3,Noleggio.EnumStato.CONCLUSO, new BigDecimal("300.00"), Date.valueOf("2024-03-05"), Date.valueOf("2024-03-10"), Date.valueOf("2024-03-02"), user3, user4, annuncio3);
        Noleggio noleggio4 = new Noleggio(4,Noleggio.EnumStato.CONCLUSO, new BigDecimal("400.00"), Date.valueOf("2024-03-20"), Date.valueOf("2024-03-26"), Date.valueOf("2024-03-16"), user5, user8, annuncio4);
        Noleggio noleggio5 = new Noleggio(5,Noleggio.EnumStato.CONCLUSO, new BigDecimal("500.00"), Date.valueOf("2024-03-13"), Date.valueOf("2024-03-18"), Date.valueOf("2024-03-10"), user5, user6, annuncio5);
        Noleggio noleggio6 = new Noleggio(6,Noleggio.EnumStato.CONCLUSO, new BigDecimal("600.00"), Date.valueOf("2024-04-4"), Date.valueOf("2024-04-10"), Date.valueOf("2024-04-3"), user10, user8, annuncio6);
        Noleggio noleggio7 = new Noleggio(7,Noleggio.EnumStato.CONCLUSO, new BigDecimal("700.00"), Date.valueOf("2024-04-3"), Date.valueOf("2024-04-8"), Date.valueOf("2024-04-2"), user9, user7, annuncio7);
        Noleggio noleggio8 = new Noleggio(8,Noleggio.EnumStato.CONCLUSO, new BigDecimal("800.00"), Date.valueOf("2024-02-7"), Date.valueOf("2024-02-16"), Date.valueOf("2024-02-4"), user14, user12, annuncio8);
        Noleggio noleggio9 = new Noleggio(9,Noleggio.EnumStato.CONCLUSO, new BigDecimal("900.00"), Date.valueOf("2024-03-01"), Date.valueOf("2024-03-04"), Date.valueOf("2024-02-27"), user15, user13, annuncio9);
        Noleggio noleggio10= new Noleggio(10,Noleggio.EnumStato.CONCLUSO,new BigDecimal("99.00"), Date.valueOf("2024-03-09"),Date.valueOf("2024-03-15"), Date.valueOf("2024-03-03"), user10, user1, annuncio10);
        Noleggio noleggio11 = new Noleggio(11,Noleggio.EnumStato.RICHIESTA, new BigDecimal("650.00"), Date.valueOf("2024-04-4"), Date.valueOf("2024-04-12"), Date.valueOf("2024-04-1"), user1, user2, annuncio1);
        Noleggio noleggio16 = new Noleggio(16,Noleggio.EnumStato.RICHIESTA, new BigDecimal("600.00"), Date.valueOf("2024-04-02"), Date.valueOf("2024-04-11"), Date.valueOf("2024-03-28"), user1, user2, annuncio2);
        Noleggio noleggio12= new Noleggio(12,Noleggio.EnumStato.RICHIESTA, new BigDecimal("700.00"), Date.valueOf("2024-04-5"), Date.valueOf("2024-04-8"), Date.valueOf("2024-04-1"), user9, user7, annuncio7);
        Noleggio noleggio13= new Noleggio(13,Noleggio.EnumStato.RICHIESTA, new BigDecimal("800.00"), Date.valueOf("2024-02-7"), Date.valueOf("2024-02-16"), Date.valueOf("2024-02-4"), user14, user12, annuncio8);
        Noleggio noleggio14= new Noleggio(14,Noleggio.EnumStato.RICHIESTA, new BigDecimal("900.00"), Date.valueOf("2024-03-01"), Date.valueOf("2024-03-04"), Date.valueOf("2024-02-27"), user15, user13, annuncio9);
        Noleggio noleggio15= new Noleggio(15,Noleggio.EnumStato.RICHIESTA,new BigDecimal("99.00"), Date.valueOf("2024-03-09"),Date.valueOf("2024-03-15"), Date.valueOf("2024-03-03"), user10, user1, annuncio10);
        Noleggio noleggio17= new Noleggio(17, Noleggio.EnumStato.CONCLUSO, new BigDecimal("188.00"), Date.valueOf("2024-01-23"), Date.valueOf("2024-01-24"), Date.valueOf("2024-01-23"), user9, user1, annuncio13);

        this.noleggioService.save(noleggio1);
        this.noleggioService.save(noleggio2);
        this.noleggioService.save(noleggio3);
        this.noleggioService.save(noleggio4);
        this.noleggioService.save(noleggio5);
        this.noleggioService.save(noleggio6);
        this.noleggioService.save(noleggio7);
        this.noleggioService.save(noleggio8);
        this.noleggioService.save(noleggio9);
        this.noleggioService.save(noleggio10);
        this.noleggioService.save(noleggio11);
        this.noleggioService.save(noleggio12);
        this.noleggioService.save(noleggio13);
        this.noleggioService.save(noleggio14);
        this.noleggioService.save(noleggio15);
        this.noleggioService.save(noleggio16);
        this.noleggioService.save(noleggio17);


        // Aggiunta Valutazioni Utente

        this.valutazioneUtenteService.save(new ValutazioneUtente(1,8, "Transazione veloce e comunicazione cordiale.", user2, user1, noleggio1));
        this.valutazioneUtenteService.save(new ValutazioneUtente(2,9, "Ottimo acquirente, pagamento puntuale e piacevole interazione.", user1, user2,noleggio1));
        this.valutazioneUtenteService.save(new ValutazioneUtente(3,7, "Buon venditore, spedizione rapida e disponibilità.", user4, user3, noleggio3));
        this.valutazioneUtenteService.save(new ValutazioneUtente(4,10, "Consigliato! Persona affidabile, cortese e rispettosa.", user3, user4,noleggio3));
        this.valutazioneUtenteService.save(new ValutazioneUtente(5,2, "Interazione complicata, problemi di comunicazione.", user6, user5, noleggio5));
        this.valutazioneUtenteService.save(new ValutazioneUtente(6,8, "Venditore affidabile, risposte rapide e gentili.", user7, user9, noleggio7));
        this.valutazioneUtenteService.save(new ValutazioneUtente(7,10, "Cliente eccezionale, sempre cortese e rispettoso.", user9, user7, noleggio7 ));
        this.valutazioneUtenteService.save(new ValutazioneUtente(8,5, "Ritardo nella comunicazione, esperienza meno positiva.", user5, user6, noleggio5));
        this.valutazioneUtenteService.save(new ValutazioneUtente(9,9, "Acquirente serio, piacevole da trattare e affidabile.", user10, user8, noleggio6));
        this.valutazioneUtenteService.save(new ValutazioneUtente(10,3, "Pessima esperienza, scarsa comunicazione e mancanza di rispetto.", user1, user10, noleggio10));
        this.valutazioneUtenteService.save(new ValutazioneUtente(11,6, "Buona comunicazione durante la transazione.", user8, user10, noleggio6));
        this.valutazioneUtenteService.save(new ValutazioneUtente(12,7, "Pagamento rapido, transazione piacevole.", user3, user2, noleggio2));
        this.valutazioneUtenteService.save(new ValutazioneUtente(13,9, "Persona affidabile, prodotto ricevuto in perfette condizioni.", user2, user3,noleggio2));
        this.valutazioneUtenteService.save(new ValutazioneUtente(14,5, "Inesattezze nella descrizione personale.", user14, user12, noleggio8));
        this.valutazioneUtenteService.save(new ValutazioneUtente(15,8, "Imballaggio professionale e spedizione veloce.", user12, user14, noleggio8));
        this.valutazioneUtenteService.save(new ValutazioneUtente(16,8, "Venditore molto disponibile, nessun problema con la consegna.", user8, user5, noleggio4));
        this.valutazioneUtenteService.save(new ValutazioneUtente(17,10, "Cliente eccezionale, sempre soddisfatto della nostra interazione.", user5, user8, noleggio4));
        this.valutazioneUtenteService.save(new ValutazioneUtente(18,6, "Prodotto danneggiato durante la spedizione, venditore cordiale.", user13, user15, noleggio9));
        this.valutazioneUtenteService.save(new ValutazioneUtente(19,9, "Transazione senza intoppi, acquirente affidabile.", user15, user13, noleggio9));
        this.valutazioneUtenteService.save(new ValutazioneUtente(20,2, "Risposte tardive, esperienza negativa nella nostra interazione.", user10, user1, noleggio10));


        // Aggiunta Valutazioni Oggetto

        this.valutazioneOggettoService.save(new ValutazioneOggetto(1,9, "Prodotto di ottima qualità, corrisponde alla descrizione.", annuncio1, user1, noleggio1));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(2,8, "Consegna rapida, imballaggio curato.", annuncio2, user3, noleggio2));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(3,7, "Prodotto leggermente diverso dalla descrizione.", annuncio3, user3, noleggio3));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(4,9, "Molto soddisfatto dell'acquisto, ottimo rapporto qualità-prezzo.", annuncio4, user5, noleggio4));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(5,4, "Prodotto difettoso, per nulla soddisfatto.", annuncio5, user5, noleggio5));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(6,8, "Venditore affidabile, prodotto conforme alla descrizione.", annuncio6, user10, noleggio6));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(7,9, "Prodotto esattamente come descritto, spedizione veloce.", annuncio7, user9, noleggio7));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(8,10, "Prodotto ricevuto in perfette condizioni.", annuncio8, user14, noleggio8));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(9,6, "Prodotto un po' danneggiato durante la spedizione.", annuncio9, user15, noleggio9));
        this.valutazioneOggettoService.save(new ValutazioneOggetto(10,2, "Prodotto non funzionante, molto deluso.", annuncio10, user10, noleggio10));

        this.chatService.save(new Messaggio("Ciao", Timestamp.valueOf("2024-01-23 10:00:00"), user1, user2));
        this.chatService.save(new Messaggio("Ciao!", Timestamp.valueOf("2024-01-23 11:30:00"), user2, user1));
        this.chatService.save(new Messaggio("La via dell'annuncio è corretta?", Timestamp.valueOf("2024-01-23 13:45:00"), user1, user2));
        this.chatService.save(new Messaggio("Si", Timestamp.valueOf("2024-01-23 15:20:00"), user2, user1));
        this.chatService.save(new Messaggio("Perfetto", Timestamp.valueOf("2024-01-23 16:40:00"), user1, user2));
        this.chatService.save(new Messaggio("Ciao", Timestamp.valueOf("2024-01-24 09:15:00"), user3, user4));
        this.chatService.save(new Messaggio("Ciao", Timestamp.valueOf("2024-01-24 11:00:00"), user4, user3));
        this.chatService.save(new Messaggio("Posso venire a qualunque ora?", Timestamp.valueOf("2024-01-24 14:30:00"), user3, user4));
        this.chatService.save(new Messaggio("si sono libero tutta la giornata", Timestamp.valueOf("2024-01-24 16:00:00"), user4, user3));
        this.chatService.save(new Messaggio("Ok va bene",Timestamp.valueOf( "2024-01-25 08:45:00"), user3, user4));


    }

}
