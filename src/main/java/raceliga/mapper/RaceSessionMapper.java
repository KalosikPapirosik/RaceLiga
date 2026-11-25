package raceliga.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import raceliga.dto.*;
import raceliga.model.RaceSessionSummary;
import raceliga.models.entities.RaceSession;

@Component
public class RaceSessionMapper {

    public RaceSession toEntity(RaceSessionCreateRequest sourse){
        RaceSession entity = new RaceSession();
        entity.setPrize(sourse.prize());
        entity.setTrack(sourse.track());
        entity.setDescription(sourse.description());
        entity.setName(sourse.track());
        return entity;
    }

    public AddRaceSessionDTO toCommand(RaceSessionCreateRequest source) {
        AddRaceSessionDTO result = new AddRaceSessionDTO();
        result.setName(source.name());
        result.setTrack(source.track());
        result.setDescription(source.description());
        result.setPrize(source.prize());
        return result;
    }



    public RaceSessionSummaryResponse toSummaryResponse(RaceSessionSummary sourse){
        RaceSessionSummaryResponse summaryResponse = new RaceSessionSummaryResponse(sourse.id(), sourse.name(), sourse.track(), sourse.description());
        return summaryResponse;
    }

    public RaceSessionDetailsResponse toDetailsResponse(RaceSessionSummary sourse){
        RaceSessionDetailsResponse detailsResponse = new RaceSessionDetailsResponse(sourse.id(), sourse.name(), sourse.track(), sourse.description(), sourse.prize());
        return detailsResponse;
    }

    public PageResponse<RaceSessionSummaryResponse> toPage(Page<RaceSessionSummary> source) {
        return new PageResponse<>(
                source.getContent().stream().map(this::toSummaryResponse).toList(),
                source.getNumber(),
                source.getSize(),
                source.getTotalElements(),
                source.getTotalPages(),
                source.isFirst(),
                source.isLast()
        );
    }


}
