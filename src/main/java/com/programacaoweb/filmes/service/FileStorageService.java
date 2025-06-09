package com.programacaoweb.filmes.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FileStorageService {
    private final Path root = Paths.get("src/main/webapp/WEB-INF/images");;

    public Optional<Path> loadRandomFileRelativePath() {
        try {
            List<Path> files = Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root)).map(this.root::relativize)
                    .toList();

            if (!files.isEmpty()) {
                Random random = new Random();
                Path selectedFile = files.get(random.nextInt(files.size()));

                return Optional.of(selectedFile);
            } else {
                return Optional.empty();
            }

        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar os arquivos da pasta: " + root, e);
        }
    }
}
