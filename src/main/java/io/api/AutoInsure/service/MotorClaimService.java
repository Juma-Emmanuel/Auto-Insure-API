package io.api.AutoInsure.service;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.MotorClaim;
import io.api.AutoInsure.entity.MotorCover;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.entity.Vehicle;
import io.api.AutoInsure.repository.MotorClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class MotorClaimService {

    @Autowired
    private MotorClaimRepository motorClaimRepository;

    public MotorClaim saveMotorClaim (MotorClaim motorClaim, Integer motorId){


        MyUserDetails currentUser = MyUserDetails.getCurrentUser();
        User user = currentUser.getUser();


        Optional<MotorCover> optionalMotorCover = user.getMotorCovers().stream()
                .filter(motorCover -> motorCover.getMotorId() == (motorId))
                .findFirst();
        if (optionalMotorCover.isPresent()) {
            MotorCover foundMotorCover = optionalMotorCover.get();
            Vehicle coveredVehicle = foundMotorCover.getVehicle();
            int randomValue ;
            do {
                randomValue = 100_000 + new Random().nextInt(900_000);
            } while (motorClaimRepository.existsBymotorClaimId(randomValue));

            motorClaim.setCoverType(foundMotorCover.getCoverType());
            motorClaim.setPolicyNumber(foundMotorCover.getPolicyNumber());
            motorClaim.setVehicleCategory(coveredVehicle.getVehicleCategory());
            motorClaim.setMotorClaimId(randomValue);
            motorClaim.setDriverId(coveredVehicle.getDriverId());
            motorClaim.setDriverName(coveredVehicle.getDriverName());
        }
        else{
            System.out.println("MotorCover not found for the authenticated user with ID: " + motorId);
        }
        return motorClaimRepository.save(motorClaim);
    }

}