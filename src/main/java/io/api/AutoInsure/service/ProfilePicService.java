package io.api.AutoInsure.service;

import io.api.AutoInsure.entity.ProfilePic;
import io.api.AutoInsure.exception.NotFoundException;
import io.api.AutoInsure.repository.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfilePicService {
    @Autowired
    private ProfilePicRepository profilePicRepository;

    private final String fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize().toString();

    public List<ProfilePic> getAllFiles() {
        return profilePicRepository.findAll();
    }

    public Optional<ProfilePic> getFileById(Long id) {
        return profilePicRepository.findById(id);
    }

    public List<ProfilePic> searchFilesByName(String fileName) {
        return profilePicRepository.findByFileNameContainingIgnoreCase(fileName);
    }

    public ProfilePic saveFile(int userId, MultipartFile file) throws IOException {
        if (!Files.exists(Paths.get(fileStorageLocation))) {
            Files.createDirectories(Paths.get(fileStorageLocation));
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(fileStorageLocation).resolve(fileName).normalize();
        Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        ProfilePic fileEntity = new ProfilePic();
        fileEntity.setUserId(userId);
        fileEntity.setFileName(fileName);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileUrl(fileName);

        return profilePicRepository.save(fileEntity);
    }
    public ProfilePic updateProfilePicture(int userId, MultipartFile file) throws IOException {
        Optional<ProfilePic> existingFileOptional = profilePicRepository.findByUserId(userId);

        if (existingFileOptional.isPresent()) {
            ProfilePic existingFile = existingFileOptional.get();

            // Delete existing file
            Files.deleteIfExists(Paths.get(existingFile.getFileUrl()));

            // Save new file
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(fileStorageLocation).resolve(fileName).normalize();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update existing file entity
            existingFile.setFileName(fileName);
            existingFile.setFileType(file.getContentType());
            existingFile.setFileUrl(fileName);

            return profilePicRepository.save(existingFile);
        } else {
            // Handle error if file not found for the user
            throw new NotFoundException("Profile picture not found for user: " + userId);
        }
    }


    public void deleteFile(Long id) {
        Optional<ProfilePic> optionalFile = profilePicRepository.findById(id);
        optionalFile.ifPresent(file -> {
            Path filePath = Paths.get(file.getFileUrl());
            try {
                Files.delete(filePath);
                profilePicRepository.deleteById(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
