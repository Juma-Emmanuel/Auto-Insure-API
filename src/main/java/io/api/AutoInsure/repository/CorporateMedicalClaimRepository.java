package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.CorporateMedicalClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CorporateMedicalClaimRepository extends JpaRepository<CorporateMedicalClaim, Integer> {
    boolean existsBycorporateClaimId(Integer corporateClaimId);
}