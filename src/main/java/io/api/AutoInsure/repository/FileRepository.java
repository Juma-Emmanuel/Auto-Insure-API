package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByFileNameContainingIgnoreCase(String fileName);
    List<FileEntity> findByFileType(String fileType);
}