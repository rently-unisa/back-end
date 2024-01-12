package it.unisa.c02.rently.rently_application.commons.responseService;

import it.unisa.c02.rently.rently_application.commons.jsonHelper.JsonHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Override
    public ResponseEntity<String> Ok(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }

    @Override
    public ResponseEntity<String> Ok() {
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @Override
    public ResponseEntity<String> InternalError(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
    }

    @Override
    public ResponseEntity<String> InternalError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @Override
    public ResponseEntity<String> Unauthorized(Object data) {
        String json = "";
        try {
            json = new JsonHelper().getJsonFromObject(data);
        }
        catch (Exception ex)
        {
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
    }

    @Override
    public ResponseEntity<String> Unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }
}