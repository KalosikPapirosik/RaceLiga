package raceliga.model;

import java.math.BigDecimal;
import java.util.UUID;

public record RaceSessionSummary(UUID id, String name, String track, String description, BigDecimal prize) {
}
