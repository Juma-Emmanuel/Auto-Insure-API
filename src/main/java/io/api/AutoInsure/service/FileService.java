package io.api.AutoInsure.service;

import io.api.AutoInsure.entity.FileEntity;
import io.api.AutoInsure.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private final String fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize().toString();

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public Optional<FileEntity> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public List<FileEntity> searchFilesByName(String fileName) {
        return fileRepository.findByFileNameContainingIgnoreCase(fileName);
    }

    public FileEntity saveFile(MultipartFile file) throws IOException {
        if (!Files.exists(Paths.get(fileStorageLocation))) {
            Files.createDirectories(Paths.get(fileStorageLocation));
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(fileStorageLocation).resolve(fileName).normalize();
        Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileUrl(filePath.toString());

        return fileRepository.save(fileEntity);
    }

    public void deleteFile(Long id) {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        optionalFile.ifPresent(file -> {
            Path filePath = Paths.get(file.getFileUrl());
            try {
                Files.delete(filePath);
                fileRepository.deleteById(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
