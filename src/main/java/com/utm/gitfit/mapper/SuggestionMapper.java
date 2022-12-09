package com.utm.gitfit.mapper;

import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.entities.Suggestion;
import com.utm.gitfit.model.request.SuggestionRequest;
import com.utm.gitfit.model.response.SuggestionResponse;
import com.utm.gitfit.service.CoachService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        uses = {CoachService.class})
public interface SuggestionMapper {

    @Mapping(source = "coachId", target = "coach")
    Suggestion mapRequestToEntity(SuggestionRequest suggestionRequest);

    @Mapping(source = "coach", target = "coachName", qualifiedByName = "getName")
    SuggestionResponse mapEntityToResponse(Suggestion suggestion);

    @Named("getName")
    static String getName(Coach coach) {
        System.out.println(coach);
        return coach.getName() + " " + coach.getLastName();
    }
}
