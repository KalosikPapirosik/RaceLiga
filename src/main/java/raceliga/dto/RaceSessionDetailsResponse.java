package raceliga.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record RaceSessionDetailsResponse(UUID id,
                                         String name,
                                         String track,
                                         String description,
                                         BigDecimal prize) {
}
