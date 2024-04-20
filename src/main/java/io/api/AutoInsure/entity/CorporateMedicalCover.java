package io.api.AutoInsure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateMedicalCover {
    
    @Id
     @Getter
    @Setter
    private int corporateId;

    @Getter
    @Setter
    private String companyName;

    @Getter
    @Setter
    private String contactPersonName;

    @Getter
    @Setter
    private String contactPersonPhone;

    @Getter
    @Setter
    private String medicalCoverType;

    @Getter
    @Setter
    private String policyNumber;

    @ManyToOne
    @JoinColumn(name = "nationalId")
    private User user;
}
