package com.example.hbs.dto;

import com.example.hbs.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String userName;

    private String userEmail;

    private String userContact;

    private Role userRole;

}
