package io.api.AutoInsure.service;

import io.api.AutoInsure.config.MyUserDetails;
import io.api.AutoInsure.entity.SelfMedicalCover;
import io.api.AutoInsure.entity.User;
import io.api.AutoInsure.repository.SelfMedicalCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SelfMedicalCoverService {
    @Autowired
    private SelfMedicalCoverRepository selfMedicalCoverRepository;


    public SelfMedicalCover saveSelfMedicalCover (SelfMedicalCover selfMedicalCover){


        int randomValue ;
        do {
            randomValue = 100_000 + new Random().nextInt(900_000);
        } while (selfMedicalCoverRepository.existsByselfId(randomValue));


        selfMedicalCover.setSelfId(randomValue);
        MyUserDetails currentUser = MyUserDetails.getCurrentUser();
        User user = currentUser.getUser();
        selfMedicalCover.setUser(user);
        return  selfMedicalCoverRepository.save(selfMedicalCover);
    }
}