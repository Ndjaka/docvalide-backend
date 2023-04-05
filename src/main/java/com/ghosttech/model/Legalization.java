package com.ghosttech.model;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Legalization {
    private UUID id;
    @NotBlank(message = "Le motif ne peut pas être vide")
    private String motif;
    @NotBlank(message = "Le moment de réception ne peut pas être vide")
    private String receipMoment;
    private boolean isLegalized;
    @NotNull(message = "La quantité ne peut pas être nulle")
    private int quantity;
    private String[] fileUrls;
    @NotNull(message = "L'ID de l'utilisateur ne peut pas être nul")
    private UUID userId;
}