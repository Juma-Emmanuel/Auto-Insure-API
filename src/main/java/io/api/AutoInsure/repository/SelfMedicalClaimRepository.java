package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.SelfMedicalClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SelfMedicalClaimRepository extends JpaRepository <SelfMedicalClaim, Integer> {

    boolean existsByselfClaimId(Integer selfClaimId);
}
