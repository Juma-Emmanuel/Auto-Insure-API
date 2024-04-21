package io.api.AutoInsure.service;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.entity.Vehicle;
import io.api.AutoInsure.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VehicleService {




    @Autowired
    private VehicleRepository vehicleRepository;


    public Vehicle saveVehicle (Vehicle vehicle){


        int randomValue ;
        do {
            randomValue = 100_000 + new Random().nextInt(900_000);
        } while (vehicleRepository.existsByvehicleId(randomValue));


        vehicle.setVehicleId(randomValue);
        MyUserDetails currentUser = MyUserDetails.getCurrentUser();
        User user = currentUser.getUser();
        vehicle.setUser(user);
        return  vehicleRepository.save(vehicle);
    }




}