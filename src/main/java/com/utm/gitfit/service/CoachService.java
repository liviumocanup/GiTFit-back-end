package com.utm.gitfit.service;

import com.utm.gitfit.exception.AuthException;
import com.utm.gitfit.exception.EntityInvalidInputException;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.CoachMapper;
import com.utm.gitfit.model.client.ApiException;
import com.utm.gitfit.model.client.api.PayWithConnectApi;
import com.utm.gitfit.model.client.model.PaymentTokenRequestBody;
import com.utm.gitfit.model.client.model.PaymentTokenRequestBodyData;
import com.utm.gitfit.model.client.model.PaymentTokenResponse;
import com.utm.gitfit.model.client.model.SEPAPaymentAttributes;
import com.utm.gitfit.model.dto.CoachDetailsDto;
import com.utm.gitfit.model.entities.*;
import com.utm.gitfit.model.request.ScheduleRequest;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.repository.BillingDetailsRepository;
import com.utm.gitfit.repository.CoachRepository;
import com.utm.gitfit.repository.ScheduledSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final UserService userService;
    private final BillingDetailsRepository billingDetailsRepository;
    private final PayWithConnectApi payWithConnectApi;
    private final ScheduledSessionRepository scheduledSessionRepository;

    @Transactional(readOnly = true)
    public List<CoachResponse> findAll() {
        return coachMapper.mapEntityListToResponseList(coachRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CoachResponse findById(Long id) {
        return coachMapper.mapEntityToResponse(getById(id));
    }

    @Transactional(readOnly = true)
    public List<CoachResponse> findByName(String name) {
        return coachMapper.mapEntityListToResponseList(getByName(name));
    }

    public List<Coach> getByName(String name) {
        return coachRepository.findByNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(name, name);
    }

    public Coach getById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + ", not found."));
    }

    @Transactional
    public PaymentTokenResponse scheduleSession(ScheduleRequest scheduleRequest) throws ApiException {
        Coach coach = getById(scheduleRequest.coachId());
        if (userService.getCurrentUser() instanceof Client client) {
            if (coach.getRatePerHour() != null) {
                Double price = coach.getRatePerHour() * scheduleRequest.hours();
                ScheduledSession scheduledSession = new ScheduledSession();
                scheduledSession.setCoach(coach);
                scheduledSession.setClient(client);
                scheduledSession.setDuration(scheduleRequest.hours());
                scheduledSession.setDateAndTime(scheduleRequest.date());
                scheduledSessionRepository.save(scheduledSession);

                return processSessionPayment(price, client);
            } else {
                throw new EntityInvalidInputException("Coach didn't set their Rate Per Hour yet! Kindly ask them to in order to proceed with scheduling.");
            }
        } else throw new AuthException("User unauthorized.");
    }

    private PaymentTokenResponse processSessionPayment(Double price, Client client) throws ApiException {
        PaymentTokenRequestBody paymentRequestBody = new PaymentTokenRequestBody();
        PaymentTokenRequestBodyData paymentTokenRequestBodyData = new PaymentTokenRequestBodyData();
        paymentTokenRequestBodyData.setCustomerId(client.getSaltEdgeIdentifier());
        paymentTokenRequestBodyData.setProviderCode("fake_client_xf");
        paymentTokenRequestBodyData.setTemplateIdentifier("SEPA");
        paymentTokenRequestBodyData.setReturnTo("http://google.com/");
        paymentTokenRequestBodyData.setShowConsentConfirmation(true);
        SEPAPaymentAttributes sepaPaymentAttributes = generateSEPAPaymentAttributes(client, price);
        paymentTokenRequestBodyData.setPaymentAttributes(sepaPaymentAttributes);
        paymentRequestBody.setData(paymentTokenRequestBodyData);

        return payWithConnectApi.paymentsConnectPost(paymentRequestBody);
    }

    private SEPAPaymentAttributes generateSEPAPaymentAttributes(User user, Double price) {
        SEPAPaymentAttributes sepaPaymentAttributes = new SEPAPaymentAttributes();
        BillingDetails billingDetails = billingDetailsRepository.findByUser_Id(user.getId()).orElseThrow(() -> new EntityNotFoundException("You didn't set your Billing Information yet! Please do in order to proceed with scheduling."));
        sepaPaymentAttributes.setCreditorIban(billingDetails.getIban());
        sepaPaymentAttributes.setCurrencyCode("EUR");
        sepaPaymentAttributes.setAmount(price.toString());
        sepaPaymentAttributes.setCreditorTown(billingDetails.getTown());
        sepaPaymentAttributes.setMode(SEPAPaymentAttributes.ModeEnum.NORMAL);
        sepaPaymentAttributes.setCreditorPostCode(billingDetails.getPostCode());
        sepaPaymentAttributes.setCreditorStreetName(billingDetails.getStreet());
        sepaPaymentAttributes.setCreditorName(user.getName());
        sepaPaymentAttributes.setCustomerIpAddress("10.0.0.1");
        sepaPaymentAttributes.setReference("p:131313131313131313");
        sepaPaymentAttributes.setDescription("description");
        sepaPaymentAttributes.setEndToEndId("#123123123");

        return sepaPaymentAttributes;
    }

    @Transactional(readOnly = true)
    public List<ScheduledSession> getAllScheduledSessionsByDate(LocalDate date) {
        return null;
    }

    public void addCoachDetails(CoachDetailsDto coachDetailsDto) {
        Coach coach = (Coach) userService.getCurrentUser();
        coach.setGymAddress(coach.getGymAddress());
        coach.setAboutMe(coachDetailsDto.aboutMe());
        coach.setRatePerHour(coachDetailsDto.ratePerHour());
        userService.save(coach);
    }
}
