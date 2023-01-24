package com.utm.gitfit.mapper;

import com.utm.gitfit.model.dto.PendingConnectionRequest;
import com.utm.gitfit.model.entities.ConnectionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConnectionRequestMapper {

    @Mapping(source = "id", target = "id")
    List<PendingConnectionRequest> map(List<ConnectionRequest> connectionRequest);
}
