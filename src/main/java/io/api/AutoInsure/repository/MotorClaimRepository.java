package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.MotorClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorClaimRepository extends JpaRepository<MotorClaim, Integer > {
    boolean existsBymotorClaimId(Integer motorClaimId);

}