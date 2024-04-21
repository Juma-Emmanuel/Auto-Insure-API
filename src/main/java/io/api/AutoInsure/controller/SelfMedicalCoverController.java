package io.api.AutoInsure.controller;


import io.api.AutoInsure.entity.SelfMedicalCover;
import io.api.AutoInsure.service.SelfMedicalCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfMedicalCoverController {

    @Autowired
    private SelfMedicalCoverService selfMedicalCoverService;

    @PostMapping("/addselfcover")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String addSelfMedicalCover (@RequestBody SelfMedicalCover selfMedicalCover){
        selfMedicalCoverService.saveSelfMedicalCover(selfMedicalCover);
        return "self cover added succesfully";

    }

}
