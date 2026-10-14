package raceliga.model;

import java.math.BigDecimal;

public record RaceSessionSummary(int id, String name, String track, String description, BigDecimal prize) {
}
