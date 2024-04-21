package io.api.AutoInsure.service;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.CorporateMedicalCover;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.repository.CorporateMedicalCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CorporateMedicalCoverService {
    @Autowired
    private CorporateMedicalCoverRepository corporateMedicalCoverRepository;


    public CorporateMedicalCover saveCorporateMedicalCover (CorporateMedicalCover corporateMedicalCover){


        int randomValue ;
        do {
            randomValue = 100_000 + new Random().nextInt(900_000);
        } while (corporateMedicalCoverRepository.existsBycorporateId(randomValue));


        corporateMedicalCover.setCorporateId(randomValue);
        MyUserDetails currentUser = MyUserDetails.getCurrentUser();
        User user = currentUser.getUser();
        corporateMedicalCover.setUser(user);
        return  corporateMedicalCoverRepository.save(corporateMedicalCover);
    }
}