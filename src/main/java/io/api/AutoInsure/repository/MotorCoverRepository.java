package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.MotorCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorCoverRepository extends JpaRepository<MotorCover, Integer > {
    boolean existsBymotorId(Integer motorId);
}
