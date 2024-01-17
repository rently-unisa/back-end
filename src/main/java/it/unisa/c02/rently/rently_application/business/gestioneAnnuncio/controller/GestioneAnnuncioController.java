package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gestione-annuncio")
@CrossOrigin(
        origins = {
                "*",
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
public class GestioneAnnuncioController {

    private final FilesStorageService storageService;
    private final ResponseService responseService;
    private final GestioneAnnuncioService gestioneAnnuncioService;
    private final GestioneAreaPersonaleService gestioneAreaPersonaleService;
    private final static String uploadsPath = "annunci";

    @GetMapping("/visualliza-annuncio")
    public ResponseEntity<String> getAnnuncio(@RequestParam long id)
    {
        try {
            Annuncio item = gestioneAnnuncioService.getAnnuncio(id).orElse(null);
            return responseService.Ok(item);

        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }

    @GetMapping("/visualliza-annunci-utente")
    public ResponseEntity<String> getAnnunciUtente(@RequestParam long id)
    {
        try {
            Utente u = gestioneAreaPersonaleService.getDatiPrivati(id);
            List<Annuncio> list = gestioneAnnuncioService.findAllByUtente(u);

            return responseService.Ok(list);
        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }


    @PostMapping("/aggiungi-annuncio")
    public ResponseEntity<String> addAnnuncio(@RequestPart("model") AnnuncioDTO data,
                                @RequestPart("images") MultipartFile[] files) {

        try {


            Annuncio item = new Annuncio();

            item.setNome(data.getNome());
            item.setStrada(data.getStrada());
            item.setCitta(data.getCitta());
            item.setCAP(data.getCAP());
            item.setDescrizione(data.getDescrizione());
            item.setPrezzo(data.getPrezzo());
            item.setImmagine(data.getImmagine());
            item.setCategoria(Annuncio.EnumCategoria.valueOf(data.getCategoria()));
            item.setCondizione(Annuncio.EnumCondizione.valueOf(data.getCondizione()));
            item.setDataFine(data.getDataFine());

            Utente user = gestioneAreaPersonaleService.getDatiPrivati(data.getIdUtente());
            if (user != null) {
                item.setUtente(user);
            }

            Annuncio newItem = gestioneAnnuncioService.addAnnuncio(item);
            String basePath = uploadsPath + "/" + newItem.getId();
            storageService.init(basePath);

            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                String fileName = storageService.generateRandomFileName();
                String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
                fileName = (new StringBuilder()).append(fileName).append(".").append(extension).toString();

                storageService.save(file, fileName);
                fileNames.add(file.getOriginalFilename());
            });

            return responseService.Ok(newItem);

        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }
}
