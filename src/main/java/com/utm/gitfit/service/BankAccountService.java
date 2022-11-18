package com.utm.gitfit.service;

import com.utm.gitfit.exception.EntityInvalidInputException;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.entities.BankAccount;
import com.utm.gitfit.model.entities.CardCredentials;
import com.utm.gitfit.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public BankAccount save(BankAccount bankAccount, CardCredentials cardCredentials) {
        List<CardCredentials> cardCredentialsList;

        if (bankAccount.getCardCredentialsList() == null) {
            bankAccount.setCardCredentialsList(new ArrayList<>(Collections.singletonList(cardCredentials)));
        } else if (!bankAccount.getCardCredentialsList().contains(cardCredentials)) {
            cardCredentialsList = bankAccount.getCardCredentialsList();
            cardCredentialsList.add(cardCredentials);
            bankAccount.setCardCredentialsList(cardCredentialsList);
        } else {
            throw new EntityInvalidInputException("Card with id: "+cardCredentials.getId()+", already found in Bank Account "+bankAccount.getId()+".");
        }
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public BankAccount update(BankAccount bankAccount) {
        BankAccount repoBankAccount = findBankAccountById(bankAccount.getId());
        List<CardCredentials> repoList = repoBankAccount.getCardCredentialsList();
        List<CardCredentials> newList = bankAccount.getCardCredentialsList();
        newList.removeAll(repoList);
        repoList.addAll(newList);

        repoBankAccount.setId(repoBankAccount.getId())
                .setCardCredentialsList(repoList);

        return bankAccountRepository.save(repoBankAccount);
    }

    private BankAccount findBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("BankAccount with id: " + id + " not found."));
    }
}
