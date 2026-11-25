package raceliga.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class AddRaceSessionDTO {

    @NotBlank(message = "Введите название компании")
    @Size(max = 100, message = "Не более 100 символов")
    private String name;

    @NotBlank(message = "Введите название трэка")
    @Size(max = 100, message = "Не более 100 символов")
    private String track;

    @NotBlank(message = "Введите описание трека")
    @Size(min = 10, max = 2000, message = "Не более 500 символов")
    private String description;

    @NotNull(message = "Введите бюджет")
    @DecimalMin(value = "0.01", message = "Бюджет должен быть больше нуля")
    private BigDecimal prize;

    public @NotBlank(message = "Введите название компании") @Size(max = 100, message = "Не более 100 символов") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Введите название компании") @Size(max = 100, message = "Не более 100 символов") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Введите название трэка") @Size(max = 100, message = "Не более 100 символов") String getTrack() {
        return track;
    }

    public void setTrack(@NotBlank(message = "Введите название трэка") @Size(max = 100, message = "Не более 100 символов") String track) {
        this.track = track;
    }

    public @NotBlank(message = "Введите описание трека") @Size(min = 10, max = 2000, message = "Не более 500 символов") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Введите описание трека") @Size(min = 10, max = 2000, message = "Не более 500 символов") String description) {
        this.description = description;
    }

    public @NotNull(message = "Введите бюджет") @DecimalMin(value = "0.01", message = "Бюджет должен быть больше нуля") BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(@NotNull(message = "Введите бюджет") @DecimalMin(value = "0.01", message = "Бюджет должен быть больше нуля") BigDecimal prize) {
        this.prize = prize;
    }
}
