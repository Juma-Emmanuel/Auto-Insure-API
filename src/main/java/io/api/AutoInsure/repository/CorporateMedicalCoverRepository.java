package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.CorporateMedicalCover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateMedicalCoverRepository extends JpaRepository<CorporateMedicalCover, Integer> {
    boolean existsBycorporateId(Integer corporateId);

}
