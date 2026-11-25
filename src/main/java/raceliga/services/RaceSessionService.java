package raceliga.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raceliga.dto.AddRaceSessionDTO;
import raceliga.model.RaceSessionSummary;

import java.util.List;
import java.util.UUID;

public interface RaceSessionService {
    List<RaceSessionSummary> allRaces();
    RaceSessionSummary raceDetails(String raceName);
    RaceSessionSummary addRaceSession(AddRaceSessionDTO added);
    void addRaceSessions(List<AddRaceSessionDTO> forms);
    void updateRaceSession(String raceName, AddRaceSessionDTO sourse);
    void removeRaceSession(String raceSession);

    RaceSessionSummary raceDetails(UUID raceId);
    void removeRaceSession(UUID raceId);
    RaceSessionSummary updateRaceSession(UUID id, AddRaceSessionDTO raceSessionDTO);

    Page<RaceSessionSummary> allRacesPaginated(Pageable pageable);
}
