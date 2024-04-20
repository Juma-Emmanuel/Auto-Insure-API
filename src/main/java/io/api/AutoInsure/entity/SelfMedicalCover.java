package io.api.AutoInsure.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfMedicalCover {

    @Id
    @Getter
    @Setter
    private int selfId;
     @ManyToOne
    @JoinColumn(name = "nationalId")
    private User user;

    @Getter
    @Setter
    private String membershipType;

    @Getter
    @Setter
    private int familyMembers;

    @Getter
    @Setter
    private String medicalCoverType;

    @Getter
    @Setter
    private String policyNumber;


  


}
