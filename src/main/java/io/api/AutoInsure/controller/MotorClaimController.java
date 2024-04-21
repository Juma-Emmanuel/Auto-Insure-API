package io.api.AutoInsure.controller;

import io.api.AutoInsure.entity.MotorClaim;
import io.api.AutoInsure.service.MotorClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorClaimController {


    @Autowired
    private MotorClaimService motorClaimService;

    @PostMapping("/{motorId}/updatemotorclaim")
    public MotorClaim makeMotorClaim (@RequestBody MotorClaim motorClaim, @PathVariable Integer motorId){

        return motorClaimService.saveMotorClaim(motorClaim, motorId);

        // return null;
    }
}