package com.utm.gitfit.mapper;

import com.utm.gitfit.model.dto.BillingDetailsDto;
import com.utm.gitfit.model.entities.BillingDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillingDetailsMapper {

    BillingDetailsDto map(BillingDetails billingDetails);

    BillingDetails map(BillingDetailsDto billingDetailsDto);
}
