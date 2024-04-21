package io.api.AutoInsure.controller;

import io.api.AutoInsure.entity.SelfMedicalClaim;
import io.api.AutoInsure.service.SelfMedicalClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfMedicalClaimController {

    @Autowired
    private SelfMedicalClaimService selfMedicalClaimService;
    @PostMapping("/{selfId}/updateselfclaim")
    public SelfMedicalClaim saveSelfMedicalCover (@RequestBody SelfMedicalClaim SelfMedicalClaim, @PathVariable Integer selfId){

        return selfMedicalClaimService.saveSelfMedicalClaim(SelfMedicalClaim, selfId);
    }
}
