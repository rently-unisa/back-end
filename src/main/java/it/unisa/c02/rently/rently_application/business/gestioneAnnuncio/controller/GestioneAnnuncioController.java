package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.storageService.FilesStorageService;
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
@RequestMapping("/gestione-annuncio")
public class GestioneAnnuncioController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    ResponseService responseService;

    private final GestioneAnnuncioService gestioneAnnuncioService;
    private final GestioneAreaPersonaleService gestioneAreaPersonaleService;
    private final String uploadsPath = "annunci";
    @PostMapping("/add")
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
