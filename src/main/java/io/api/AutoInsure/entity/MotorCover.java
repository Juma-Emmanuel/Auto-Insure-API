package io.api.AutoInsure.entity;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorCover {
    @Id
    @Getter
    @Setter
    private int motorId;

     @ManyToOne
    @JoinColumn(name = "nationalId")
    private User user;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;                                            
    @Getter
    @Setter
    private String registrationNumber;
    @Getter
    @Setter
    private String policyNumber;

     @Getter
    @Setter
    private LocalDate coverExpiryDate;

    @Getter
    @Setter
     private LocalDate coverRenewalDate;

     @Getter
    @Setter
     private String coverType;
}
