package io.api.AutoInsure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@Data
public class MotorClaim {
    
    @Id
    @Getter
    @Setter  
    private int motorClaimId;

    @ManyToOne
    @JoinColumn(name = "motorId")
    private MotorCover motorCover;    
    private String coverType;
    private String policyNumber;
    private LocalDate claimDate;
    private LocalDate expiryDate;
    private LocalDate clashDate;
    private String vehicleCategory;
    private String driverId;
    private String driverName;
}
