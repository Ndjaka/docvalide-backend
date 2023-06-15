package com.ghosttech.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimony {
    private UUID id;
    @NotBlank(message = "The testimony cannot be blank")
    private String testimony;
    private boolean isActive;
    private User userId;
}
