package it.unisa.c02.rently.rently_application.commons.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Random;


public class ImageStorage {

    /*
    private final String basePath = "/static/annunci/images/";
    public String SaveImage(MultipartFile file) {

        ClassLoader classLoader = this.getClass().getClassLoader();

        String fileName = this.GenerateRandomFileName();
        String filePath = fileName = classLoader.getResource(".").getFile() + basePath + "/" + fileName;

        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        return "";
    }

    public String GenerateRandomFileName() {
        byte[] array = new byte[62]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }

     */
}


