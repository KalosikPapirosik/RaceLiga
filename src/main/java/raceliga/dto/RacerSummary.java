package raceliga.dto;

import raceliga.models.enums.RacingClass;

import java.util.UUID;

public record RacerSummary(
        UUID id,
        String firstName,
        String lastName,
        String nickname,
        Integer racingNumber,
        RacingClass racingClass,
        String raceName
) {
    public String getFullName(){
        return firstName + " " + lastName;
    }
}
