package raceliga.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RaceSessionCreateRequest(
        @NotBlank
        @Size(max = 100, min = 3)
        String name,

        @NotBlank
        @Size(max = 100, min = 3)
        String track,

        @NotBlank
        @Size(max = 2000, min = 10)
        String description,

        @NotNull(message = "Введите призовые")
        @DecimalMin(value = "0.01", message = "Бюджет должен быть больше нуля")
        @Digits(integer = 17, fraction = 2, message = "Не более 17 цифр до точки и две после нее")
        BigDecimal prize
) {
    public RaceSessionCreateRequest{
        name = name == null ? null : name.trim();
        track = track == null ? null : track.trim();
        description = description == null ? null : description.trim();
    }
}
