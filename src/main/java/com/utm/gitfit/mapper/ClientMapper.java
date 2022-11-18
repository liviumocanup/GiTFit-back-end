package com.utm.gitfit.mapper;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.model.entities.Client;
import lombok.NoArgsConstructor;

import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClientMapper {
    public static final Function<Client, ClientDto> mapToDto = client ->
            ClientDto.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .username(client.getUsername())
                    .password(client.getPassword())
                    .birthday(client.getBirthday())
                    .bankAccountId(client.getBankAccountId())
                    .traineeInfo(TraineeInfoMapper.mapToDto.apply(client.getTraineeInfo()))
                    .build();

    public static final Function<ClientDto, Client> mapToEntity = clientDto ->
            Client.builder()
                    .id(clientDto.getId())
                    .name(clientDto.getName())
                    .lastName(clientDto.getLastName())
                    .email(clientDto.getEmail())
                    .username(clientDto.getUsername())
                    .password(clientDto.getPassword())
                    .birthday(clientDto.getBirthday())
                    .bankAccountId(clientDto.getBankAccountId())
                    .traineeInfo(TraineeInfoMapper.mapToEntity.apply(clientDto.getTraineeInfo()))
                    .build();
}
