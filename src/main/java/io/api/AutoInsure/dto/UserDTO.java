package io.api.AutoInsure.dto;

import lombok.Data;

@Data
public class UserDTO {


    private String fullName;

    private String email;

    private String phoneNumber;

    private String country;

    public UserDTO(String fullName,  String email, String phoneNumber,String country) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
    }
}