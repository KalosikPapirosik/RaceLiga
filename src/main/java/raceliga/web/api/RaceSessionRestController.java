package raceliga.web.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import raceliga.dto.PageResponse;
import raceliga.dto.RaceSessionCreateRequest;
import raceliga.dto.RaceSessionDetailsResponse;
import raceliga.dto.RaceSessionSummaryResponse;
import raceliga.mapper.RaceSessionMapper;
import raceliga.services.RaceSessionService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/races", produces = MediaType.APPLICATION_JSON_VALUE)
public class RaceSessionRestController {
    private final RaceSessionService raceSessionService;
    private final RaceSessionMapper raceSessionMapper;

    public RaceSessionRestController(RaceSessionService raceSessionService, RaceSessionMapper mapper) {
        this.raceSessionService = raceSessionService;
        this.raceSessionMapper = mapper;
    }

    @GetMapping
    public PageResponse<RaceSessionSummaryResponse> races(
            @RequestParam(defaultValue = "0") @Min(0) @Max(1000000) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        var request = PageRequest.of(page, size, Sort.by("name").and(Sort.by("id")));
        return raceSessionMapper.toPage(raceSessionService.allRacesPaginated(request));
    }

    @GetMapping("/{id}")
    public RaceSessionDetailsResponse race(@PathVariable UUID id) {
        return raceSessionMapper.toDetailsResponse(raceSessionService.raceDetails(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RaceSessionDetailsResponse> create(@Valid @RequestBody RaceSessionCreateRequest request) {
        var created = raceSessionMapper.toDetailsResponse(raceSessionService.addRaceSession(raceSessionMapper.toCommand(request)));
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RaceSessionDetailsResponse replace(@PathVariable UUID id,
                                              @Valid @RequestBody RaceSessionCreateRequest request) {
        return raceSessionMapper.toDetailsResponse(raceSessionService.updateRaceSession(id, raceSessionMapper.toCommand(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        raceSessionService.removeRaceSession(id);
        return ResponseEntity.noContent().build();
    }

}
