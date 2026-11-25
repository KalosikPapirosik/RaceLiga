package raceliga.dto;

import java.util.UUID;

public record RaceSessionSummaryResponse(UUID id, String name, String track, String description) {
}
