package io.api.AutoInsure.repository;

import io.api.AutoInsure.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle, Integer>{

    boolean existsByvehicleId(Integer vehicleId);
}
