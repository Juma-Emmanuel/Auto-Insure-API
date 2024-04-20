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
public class Vehicle {
    @Id
    @Getter
    @Setter
    private int vehicleId;

    @ManyToOne
    @JoinColumn(name = "nationalId")
    private User user;

    @Getter
    @Setter
    private String registrationNumber;

    @Getter
    @Setter
    private String chasisNumber;

    @Getter
    @Setter
    private String cvNumber;

    @Getter
    @Setter
    private Float vehicleValue;

    @Getter
    @Setter
    private String manufactureYear;

    @Getter
    @Setter
    private String driverId;

    @Getter
    @Setter
    private String vehicleCategory;

    @Getter
    @Setter
    private String driverName;

    @Getter
    @Setter
    private String driverExperience;


      public void setUser(User user) {
        this.user = user;
    }

}
