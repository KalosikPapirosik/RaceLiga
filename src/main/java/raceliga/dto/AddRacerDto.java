package raceliga.dto;
import jakarta.validation.constraints.*;
import raceliga.models.enums.RacingClass;

import java.time.LocalDate;
import java.util.UUID;

public class AddRacerDto {

    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(max = 100, message = "Имя не должно превышать 100 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    @Size(max = 100, message = "Фамилия не должна превышать 100 символов")
    private String lastName;

    @NotBlank(message = "Никнейм обязателен")
    @Size(max = 50, message = "Никнейм не должен превышать 50 символов")
    private String nickname;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @NotNull(message = "Гоночный номер обязателен")
    @Min(value = 1, message = "Номер должен быть не меньше 1")
    @Max(value = 999, message = "Номер должен быть не больше 999")
    private Integer racingNumber;

    @NotNull(message = "Класс гонок обязателен")
    private RacingClass racingClass;

    @NotNull(message = "Необходимо выбрать заезд")
    private String raceName;

    public AddRacerDto() {
    }

    // Геттеры и сеттеры
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Integer getRacingNumber() { return racingNumber; }
    public void setRacingNumber(Integer racingNumber) { this.racingNumber = racingNumber; }

    public RacingClass getRacingClass() { return racingClass; }
    public void setRacingClass(RacingClass racingClass) { this.racingClass = racingClass; }

    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }
}
