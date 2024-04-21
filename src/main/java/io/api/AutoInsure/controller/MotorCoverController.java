package io.api.AutoInsure.controller;

import io.api.AutoInsure.entity.MotorCover;
import io.api.AutoInsure.service.MotorCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorCoverController {

    @Autowired

    private MotorCoverService motorCoverService;

    @PostMapping("/{vehicleId}/updatemotorcover")

    public String createMotorCoverForVehicle(@RequestBody MotorCover motorCover, @PathVariable Integer vehicleId) {

        motorCoverService.updateMotorCoverForVehicle(motorCover, vehicleId);

        return "MotorCover created successfully with ID: " + motorCover.getMotorId();


    }

}