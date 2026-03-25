package com.programacaoweb.filmes.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class FileStorageService {


    private final Path root = Paths.get("src/main/resources/static/images");

    public FileStorageService() {
        try {

            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta de upload!");
        }
    }


    public String saveFile(MultipartFile file) {
        try {

            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public Optional<Path> loadRandomFileRelativePath() {
        try {
            List<Path> files = Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize)
                    .toList();

            if (!files.isEmpty()) {
                Random random = new Random();
                Path selectedFile = files.get(random.nextInt(files.size()));
                return Optional.of(selectedFile);
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler a pasta: " + root, e);
        }
    }
}