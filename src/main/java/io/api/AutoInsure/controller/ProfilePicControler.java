package io.api.AutoInsure.controller;


import io.api.AutoInsure.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.api.AutoInsure.entity.ProfilePic;
import io.api.AutoInsure.service.ProfilePicService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class ProfilePicControler{

    @Autowired
    private ProfilePicService profilePicService;

    @GetMapping
    public ResponseEntity<List<ProfilePic>> getAllFiles() {
        List<ProfilePic> files = profilePicService.getAllFiles();
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfilePic> getFileById(@PathVariable int id) {
        return profilePicService.getFileById(id)
                .map(file -> new ResponseEntity<>(file, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfilePic>> searchFilesByName(@RequestParam String fileName) {
        List<ProfilePic> files = profilePicService.searchFilesByName(fileName);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<ProfilePic> updateProfilePicture(@PathVariable int userId, @RequestParam("file") MultipartFile file) {
        try {
            ProfilePic updatedFile = profilePicService.updateProfilePicture(userId, file);
            return new ResponseEntity<>(updatedFile, HttpStatus.OK);
        } catch (IOException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable int id) {
        profilePicService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
