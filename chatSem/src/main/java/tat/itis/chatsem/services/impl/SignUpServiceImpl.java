package tat.itis.chatsem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tat.itis.chatsem.dto.SignUpForm;
import tat.itis.chatsem.exceptions.ErrorEntity;
import tat.itis.chatsem.exceptions.ValidationException;
import tat.itis.chatsem.mapper.AccountsMapper;
import tat.itis.chatsem.models.Account;
import tat.itis.chatsem.repositories.AccountsRepository;
import tat.itis.chatsem.services.SignUpService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final AccountsRepository accountsRepository;
    private final AccountsMapper accountsMapper;
    private final Validator validator;

    @Override
    public void signUp(SignUpForm signUpForm) {
        Set<ConstraintViolation<SignUpForm>> violations = validator.validate(signUpForm);
        if(!violations.isEmpty()) {
            throw new ValidationException(violations.stream().findFirst().get().getMessage());
        }

        if (accountsRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            throw new ValidationException(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }

        Account account = accountsMapper.toAccount(signUpForm);
        accountsRepository.save(account);
    }

}
