package io.api.AutoInsure.service;


import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.CorporateMedicalCover;
import io.api.AutoInsure.entity.CorporateMedicalClaim;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.repository.CorporateMedicalClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CorporateMedicalClaimService  {

    @Autowired
    private CorporateMedicalClaimRepository corporateMedicalClaimRepository;

    public CorporateMedicalClaim saveCorporateMedicalClaim (CorporateMedicalClaim corporateMedicalClaim, Integer corporateId){
        MyUserDetails currentUser = MyUserDetails.getCurrentUser();
        User user = currentUser.getUser();

        Optional<CorporateMedicalCover> optionalCorporateMedicalCover = user.getCorporateMedicalCovers().stream()
                .filter(corporateMedicalCover -> corporateMedicalCover.getCorporateId() == (corporateId))
                .findFirst();
        if (optionalCorporateMedicalCover.isPresent()){

            CorporateMedicalCover foundCorporateMedicalCover = optionalCorporateMedicalCover.get();

            int randomValue;
            do{
                randomValue = 100_000 + new Random().nextInt(900_000);
            }while(corporateMedicalClaimRepository.existsBycorporateClaimId(randomValue));



            corporateMedicalClaim.setCorporateClaimId(user.getNationalId());
            corporateMedicalClaim.setPolicyNumber(foundCorporateMedicalCover.getPolicyNumber());
        }

        return  corporateMedicalClaimRepository.save(corporateMedicalClaim);
    }

}
