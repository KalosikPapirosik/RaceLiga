package raceliga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raceliga.models.entities.RaceSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RaceSessionRepository extends JpaRepository<RaceSession, UUID> {
    Optional<RaceSession> findByName(String name);

    boolean existsByNameIgnoreCase(String name);

    List<RaceSession> findAllByOrderByNameAsc();
}
