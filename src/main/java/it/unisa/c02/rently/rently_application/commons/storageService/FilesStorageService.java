package it.unisa.c02.rently.rently_application.commons.storageService;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init(String id);

    public void save(MultipartFile file, String fileName);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public String generateRandomFileName();
}
