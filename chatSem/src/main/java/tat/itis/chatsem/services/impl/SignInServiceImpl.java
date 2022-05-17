package tat.itis.chatsem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tat.itis.chatsem.dto.AccountDto;
import tat.itis.chatsem.dto.SignInForm;
import tat.itis.chatsem.exceptions.ErrorEntity;
import tat.itis.chatsem.exceptions.ValidationException;
import tat.itis.chatsem.mapper.AccountsMapper;
import tat.itis.chatsem.models.Account;
import tat.itis.chatsem.repositories.AccountsRepository;
import tat.itis.chatsem.services.SignInService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class SignInServiceImpl implements SignInService {
    private final AccountsRepository accountsRepository;
    private final AccountsMapper accountsMapper;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    @Override
    public AccountDto signIn(SignInForm signInForm) {
        Set<ConstraintViolation<SignInForm>> violations = validator.validate(signInForm);
        if(!violations.isEmpty()) {
            throw new ValidationException(violations.stream().findFirst().get().getMessage());
        }

        Account account = accountsRepository
                .findByEmail(signInForm.getEmail())
                .orElseThrow((Supplier<RuntimeException>)
                        () -> new ValidationException(ErrorEntity.ACCOUNT_NOT_FOUND));

        if (passwordEncoder.matches(signInForm.getPassword(), account.getPassword())) {
            return accountsMapper.toAccountDto(account);
        } else {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
    }
}
