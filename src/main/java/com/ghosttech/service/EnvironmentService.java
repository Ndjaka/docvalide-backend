package com.ghosttech.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
public class EnvironmentService {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public String getActiveEnvironment() {
        return activeProfile;
    }
}
