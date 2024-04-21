package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.SelfMedicalCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SelfMedicalCoverRepository extends JpaRepository<SelfMedicalCover, Integer> {
    boolean existsByselfId(Integer selfId);
}
