package com.utm.gitfit.mapper;

import com.utm.gitfit.model.dto.ClientDto;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.response.ClientResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BillingDetailsMapper.class, StatisticMapper.class})
public interface ClientMapper {

    ClientDto mapEntityToDto(Client client);

    Client mapDtoToEntity(ClientDto clientDto);

    ClientResponse mapEntityToResponse(Client client);

    List<ClientResponse> mapEntityListToResponseList(List<Client> clientList);
}
