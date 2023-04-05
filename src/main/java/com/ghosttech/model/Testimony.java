package com.ghosttech.model;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
