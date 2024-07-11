package io.api.AutoInsure.service;

import io.api.AutoInsure.entity.ProfilePic;
import io.api.AutoInsure.exception.NotFoundException;
import io.api.AutoInsure.repository.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${server.url}")
    private String serverUrl;
    @Autowired
    private ProfilePicRepository profilePicRepository;

    private final String fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize().toString();

    public List<ProfilePic> getAllFiles() {
        return profilePicRepository.findAll();
    }

    public Optional<ProfilePic> getFileById(int id) {
        return profilePicRepository.findByUserId(id);
    }

    public List<ProfilePic> searchFilesByName(String fileName) {
        return profilePicRepository.findByFileNameContainingIgnoreCase(fileName);
    }


    public ProfilePic updateProfilePicture(int userId, MultipartFile file) throws IOException {
        if (!Files.exists(Paths.get(fileStorageLocation))) {
            Files.createDirectories(Paths.get(fileStorageLocation));
        }
        Optional<ProfilePic> existingFileOptional = profilePicRepository.findByUserId(userId);

        if (existingFileOptional.isPresent()) {
            ProfilePic existingFile = existingFileOptional.get();
            Files.deleteIfExists(Paths.get(fileStorageLocation).resolve(existingFile.getFileName()).normalize());
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(fileStorageLocation).resolve(fileName).normalize();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


            existingFile.setFileName(fileName);
            existingFile.setFileType(file.getContentType());
            existingFile.setFileUrl(serverUrl + "/uploads/" + fileName);

            return profilePicRepository.save(existingFile);
        } else {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(fileStorageLocation).resolve(fileName).normalize();
            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            ProfilePic fileEntity = new ProfilePic();
            fileEntity.setUserId(userId);
            fileEntity.setFileName(fileName);
            fileEntity.setFileType(file.getContentType());
            fileEntity.setFileUrl(serverUrl + "/uploads/" + fileName);

            return profilePicRepository.save(fileEntity);
        }
    }




        public void deleteFile(int id) {
        Optional<ProfilePic> optionalFile = profilePicRepository.findByUserId(id);

            if (optionalFile.isPresent()) {
                ProfilePic profilePic = optionalFile.get();
                Path filePath = Paths.get(fileStorageLocation).resolve(profilePic.getFileName()).normalize();
               try {
                   Files.deleteIfExists(filePath);
                   profilePicRepository.delete(profilePic);
               }   catch(IOException e)  {
                   throw new RuntimeException("Error deleting profile picture", e);
            }

            } else {
                throw new RuntimeException("Profile picture not found" );
            }


    }



}
