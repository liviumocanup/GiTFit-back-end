package com.utm.gitfit.service;

import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.StatisticMapper;
import com.utm.gitfit.model.request.StatisticRequest;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Statistic;
import com.utm.gitfit.model.entities.Suggestion;
import com.utm.gitfit.model.request.SuggestionRequest;
import com.utm.gitfit.model.response.StatisticResponse;
import com.utm.gitfit.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final UserService userService;
    private final SuggestionService suggestionService;
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    @Transactional(readOnly = true)
    public Set<StatisticResponse> findByClient(Long clientId) {
        return statisticRepository.findByClient(getClientById(clientId)).stream()
                .map(statisticMapper::mapEntityToResponse)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public StatisticResponse findByClientAndDay(Long clientId, LocalDate day) {
        return statisticMapper.mapEntityToResponse(getByClientIdAndDate(clientId, day));
    }

    @Transactional
    public StatisticResponse create(Long clientId, StatisticRequest statisticRequest) {
        Client client = getClientById(clientId);
        Statistic statistic = statisticMapper.mapRequestToEntity(statisticRequest);
        statistic.setClient(client);
        statistic.setSuggestions(new HashSet<>());

        Statistic repoStatistic = persist(client, statistic);
        client.getStatistics().add(repoStatistic);
        userService.save(client);

        return statisticMapper.mapEntityToResponse(repoStatistic);
    }

    @Transactional
    public StatisticResponse review(Long clientId, SuggestionRequest suggestionRequest) {
        Client client = getClientById(clientId);
        Statistic repoStat = getByClientIdAndDate(client.getId(), suggestionRequest.date());
        client.getStatistics().remove(repoStat);

        Suggestion suggestion = suggestionService.create(repoStat, suggestionRequest);
        repoStat.getSuggestions().add(suggestion);
        client.getStatistics().add(repoStat);
        userService.save(client);

        return statisticMapper.mapEntityToResponse(save(repoStat));
    }

    @Transactional
    public void remove(Long clientId, LocalDate date) {
        Statistic toRemove = getByClientIdAndDate(clientId, date);
        suggestionService.remove(toRemove);
        statisticRepository.delete(toRemove);
    }

    private Statistic persist(Client client, Statistic statistic) {
        Optional<Statistic> statisticOptional = getByClientAndDate(client, statistic.getDate());

        if (statisticOptional.isPresent()) {
            Statistic repoStat = statisticOptional.get();

            client.getStatistics().remove(repoStat);
            return update(repoStat, statistic);
        }

        return save(statistic);
    }

    private Statistic update(Statistic repoStatistic, Statistic statistic) {
        repoStatistic.setWeight(statistic.getWeight())
                .setHeight(statistic.getHeight())
                .setDiet(statistic.getDiet())
                .setFatRatio(statistic.getFatRatio());

        return save(repoStatistic);
    }

    private Statistic save(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    private Statistic getByClientIdAndDate(Long clientId, LocalDate date) {
        Client client = getClientById(clientId);
        return getByClientAndDate(client, date).orElseThrow(
                () -> new EntityNotFoundException("Client: " + clientId + ", Statistic for " + date + ", not found.")
        );
    }

    private Client getClientById(Long clientId) {
        return (Client) userService.getById(clientId);
    }

    private Optional<Statistic> getByClientAndDate(Client client, LocalDate date) {
        return statisticRepository.findStatisticByClientAndDate(client, date);
    }
}
