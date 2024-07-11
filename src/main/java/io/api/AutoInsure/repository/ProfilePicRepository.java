package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {
    Optional<ProfilePic> findByUserId(int userId);

    void deleteByUserId(int userId);

    List<ProfilePic> findByFileNameContainingIgnoreCase(String fileName);
    List<ProfilePic> findByFileType(String fileType);
}
