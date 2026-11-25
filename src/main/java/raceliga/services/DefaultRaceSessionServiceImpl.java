package raceliga.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raceliga.dto.AddRaceSessionDTO;
import raceliga.model.RaceSessionSummary;
import raceliga.models.entities.RaceSession;
import raceliga.repositories.RaceSessionRepository;
import raceliga.services.exceptions.RaceSessionNotFoundException;

import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class DefaultRaceSessionServiceImpl implements RaceSessionService {

    private final RaceSessionRepository raceSessionRepository;

    public DefaultRaceSessionServiceImpl(RaceSessionRepository raceSessionRepository) {
        this.raceSessionRepository = raceSessionRepository;
    }

    @Override
    public List<RaceSessionSummary> allRaces() {
        return raceSessionRepository.findAllByOrderByNameAsc().stream().map(this::toSummary).toList();
    }

    @Override
    public RaceSessionSummary raceDetails(String raceName) {
        return toSummary(findByName(raceName));
    }

    @Override
    public RaceSessionSummary raceDetails(UUID raceId) {
        return toSummary(findRace(raceId));
    }


    @Override
    @Transactional
    public RaceSessionSummary addRaceSession(AddRaceSessionDTO form) {
        List<RaceSession> list = raceSessionRepository.findAll();
        String normalizedName = form.getName().trim();
        if(raceSessionRepository.existsByNameIgnoreCase(normalizedName)){
            throw new IllegalArgumentException("Гонка с таким названием существует");
        }
        RaceSession raceSession = new RaceSession();
        copyField(form, raceSession);
        raceSessionRepository.saveAndFlush(raceSession);
        return toSummary(raceSession);
    }

    @Override
    @Transactional
    public void addRaceSessions(List<AddRaceSessionDTO> forms) {
        for (AddRaceSessionDTO form : forms) {
            RaceSession raceSession = new RaceSession();
            copyField(form, raceSession);
            raceSessionRepository.save(raceSession);
        }
        raceSessionRepository.flush();
    }

    @Override
    @Transactional
    public void updateRaceSession(String raceName, AddRaceSessionDTO form) {
        List<RaceSession> list = raceSessionRepository.findAll();
        RaceSession raceSession = findByName(raceName);
        String normalizedName = form.getName().trim();
        if(raceSessionRepository.existsByNameIgnoreCase(normalizedName) && !raceSession.getName().equalsIgnoreCase(normalizedName) ){
            throw new IllegalArgumentException("Гонка с таким названием существует");
        }
        copyField(form, raceSession);
        raceSessionRepository.flush();
    }

    @Override
    @Transactional
    public void removeRaceSession(String raceSessionName) {
        raceSessionRepository.delete(findByName(raceSessionName));
        raceSessionRepository.flush();
    }

    private RaceSession findByName(String name){
        return raceSessionRepository.findByName(name).orElseThrow(() -> new RaceSessionNotFoundException(name));
    }

    @Override
    public Page<RaceSessionSummary> allRacesPaginated(Pageable pageable) {
        return raceSessionRepository.findAll(pageable).map(this::toSummary);
    }

    @Override
    @Transactional
    public void removeRaceSession(UUID raceId) {
        raceSessionRepository.delete(findRace(raceId));
        raceSessionRepository.flush();
    }

    @Override
    @Transactional
    public RaceSessionSummary updateRaceSession(UUID id, AddRaceSessionDTO raceSessionDTO) {
        RaceSession raceSession = findRace(id);
        String newName = raceSessionDTO.getName().trim();
        if (!raceSession.getName().equalsIgnoreCase(newName)
                && raceSessionRepository.existsByNameIgnoreCase(newName)) {
            throw new IllegalArgumentException("Гонка с таким названием существует");
        }
        copyField(raceSessionDTO, raceSession);
        raceSessionRepository.flush();
        return toSummary(raceSession);
    }

    private void copyField(AddRaceSessionDTO sourse, RaceSession target){
        target.setName(sourse.getName());
        target.setDescription(sourse.getDescription());
        target.setTrack(sourse.getTrack());
        target.setPrize(sourse.getPrize());
    }

    private RaceSession findRace(UUID id) {
        return raceSessionRepository.findById(id)
                .orElseThrow(() -> new RaceSessionNotFoundException(id.toString()));
    }

    private RaceSessionSummary toSummary(RaceSession raceSession) {
        return new RaceSessionSummary(raceSession.getId(), raceSession.getName(), raceSession.getTrack(), raceSession.getDescription(), raceSession.getPrize());
    }
}
