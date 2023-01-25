package com.utm.gitfit.mapper;

import com.utm.gitfit.model.entities.User;
import com.utm.gitfit.model.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {BillingDetailsMapper.class})
public interface UserMapper {

    UserResponse mapEntityToResponse(User user);
}