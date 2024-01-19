package it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.controller;
import it.unisa.c02.rently.rently_application.business.gestioneAnnuncio.service.GestioneAnnuncioService;
import it.unisa.c02.rently.rently_application.business.gestioneAreaPersonale.service.GestioneAreaPersonaleService;
import it.unisa.c02.rently.rently_application.commons.services.responseService.ResponseService;
import it.unisa.c02.rently.rently_application.commons.services.storageService.FilesStorageService;
import it.unisa.c02.rently.rently_application.data.dto.AnnuncioDTO;
import it.unisa.c02.rently.rently_application.data.model.Annuncio;
import it.unisa.c02.rently.rently_application.data.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/annuncio")
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
    private final HttpServletRequest httpServletRequest;
    private final ResourceLoader resourceLoader;
    private final static String uploadsPath = "annunci";

    @Value("${uploads.path}")
    private String uploadPath;

    @GetMapping("/visualizza-annuncio")
    public ResponseEntity<String> getAnnuncio(@RequestParam long id)
    {
        try {
            Annuncio annuncio = gestioneAnnuncioService.getAnnuncio(id).orElse(null);
            AnnuncioDTO item = new AnnuncioDTO().convertFromModel(annuncio);

            String serverAddress = String.format(
                    "%s://%s:%d",
                    httpServletRequest.getScheme(),
                    httpServletRequest.getServerName(),
                    httpServletRequest.getServerPort());

            item.setServerImage(annuncio, serverAddress);

            return responseService.Ok(item);

        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }

    @GetMapping("/visualizza-annunci-utente")
    public ResponseEntity<String> getAnnunciUtente(@RequestParam long id)
    {
        try {
            Utente u = gestioneAreaPersonaleService.getDatiPrivati(id);
            List<Annuncio> annunci = gestioneAnnuncioService.findAllByUtente(u);
            List<AnnuncioDTO> list = new ArrayList<AnnuncioDTO>();

            String serverAddress = String.format(
                    "%s://%s:%d",
                    httpServletRequest.getScheme(),
                    httpServletRequest.getServerName(),
                    httpServletRequest.getServerPort());

            for (Annuncio a: annunci) {
                AnnuncioDTO item = new AnnuncioDTO().convertFromModel(a);
                item.setServerImage(a, serverAddress);
                list.add(item);
            }

            return responseService.Ok(list);
        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }


    @PostMapping(value = "aggiungi-annuncio")
    public ResponseEntity<String> addAnnuncio(@ModelAttribute("model") AnnuncioDTO model,
                                              @RequestParam("image") MultipartFile image) {

        try {


            Annuncio item = new Annuncio();

            item.setNome(model.getNome());
            item.setStrada(model.getStrada());
            item.setCitta(model.getCitta());
            item.setCap(model.getCap());
            item.setDescrizione(model.getDescrizione());
            item.setPrezzo(model.getPrezzo());
            item.setCategoria(Annuncio.EnumCategoria.valueOf(model.getCategoria().toUpperCase()));
            item.setCondizione(Annuncio.EnumCondizione.valueOf(model.getCondizione().toUpperCase()));
            java.sql.Date date = Date.valueOf(model.getDataFine());
            item.setDataFine(date);

            Utente user = gestioneAreaPersonaleService.getDatiPrivati(model.getIdUtente());
            if (user != null) {
                item.setUtente(user);
            }


            Annuncio newItem = gestioneAnnuncioService.addAnnuncio(item);
            ClassLoader classLoader = getClass().getClassLoader();

            String resourcePath = uploadPath ;
            String basePath = resourcePath + "annunci" + "\\" + newItem.getId() + "\\";
            storageService.init(basePath);

            String fileName = storageService.generateRandomFileName();
            String extension = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.') + 1);
            fileName = (new StringBuilder()).append(fileName).append(".").append(extension).toString();

            storageService.save(image, fileName);
            newItem.setImmagine(fileName);

            gestioneAnnuncioService.updateAnnuncio(newItem);
            AnnuncioDTO annuncioDto = new AnnuncioDTO().convertFromModel(newItem);


            return responseService.Ok(annuncioDto);

        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }

    @GetMapping("/delete-annuncio")
    public ResponseEntity<String> deleteAnnuncio(@RequestParam long id) {
        try {
            gestioneAnnuncioService.deleteAnnuncio(id);
            return responseService.Ok();
        }
        catch (Exception ex) {
            return responseService.InternalError();
        }
    }
}
