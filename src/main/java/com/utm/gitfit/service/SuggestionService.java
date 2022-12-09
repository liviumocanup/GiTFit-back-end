package com.utm.gitfit.service;


import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.SuggestionMapper;
import com.utm.gitfit.model.entities.Statistic;
import com.utm.gitfit.model.entities.Suggestion;
import com.utm.gitfit.model.request.SuggestionRequest;
import com.utm.gitfit.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    @Transactional
    public Suggestion create(Statistic statistic, SuggestionRequest suggestionRequest) {
        Suggestion suggestion = suggestionMapper.mapRequestToEntity(suggestionRequest);
        suggestion.setStatistic(statistic);

        return persist(statistic, suggestion);
    }

    @Transactional
    public void remove(Statistic statistic){
        Set<Suggestion> toRemove = suggestionRepository.findByStatistic(statistic);
        suggestionRepository.deleteAll(toRemove);
    }

    public Suggestion persist(Statistic statistic, Suggestion suggestion) {
        Optional<Suggestion> suggestionOptional = getByCoachAndStatisticId(suggestion);

        if (suggestionOptional.isPresent()) {
            Suggestion repoSuggestion = suggestionOptional.get();

            statistic.getSuggestions().remove(repoSuggestion);
            return update(repoSuggestion, suggestion);
        }

        return save(suggestion);
    }

    private Suggestion update(Suggestion repoSuggestion, Suggestion oldSuggestion){
        repoSuggestion.setSuggestion(oldSuggestion.getSuggestion())
                .setStatistic(oldSuggestion.getStatistic());

        return save(repoSuggestion);
    }

    private Suggestion save(Suggestion suggestion){
        return suggestionRepository.save(suggestion);
    }

    private Optional<Suggestion> getByCoachAndStatisticId(Suggestion suggestion){
        return suggestionRepository.findSuggestionByCoachAndStatistic_Id(
                suggestion.getCoach(),
                suggestion.getStatistic().getId()
        );
    }
}
