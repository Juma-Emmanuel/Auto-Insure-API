package io.api.AutoInsure.controller;

import io.api.AutoInsure.entity.CorporateMedicalCover;
import io.api.AutoInsure.service.CorporateMedicalCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorporateMedicalCoverController {
    @Autowired
    private CorporateMedicalCoverService corporateMedicalCoverService;

    @PostMapping("/addcorporatecover")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String addcorporateMedicalCover (@RequestBody CorporateMedicalCover corporateMedicalCover){


        corporateMedicalCoverService.saveCorporateMedicalCover(corporateMedicalCover);

        return "corporate cover added succesfully";

    }
}
