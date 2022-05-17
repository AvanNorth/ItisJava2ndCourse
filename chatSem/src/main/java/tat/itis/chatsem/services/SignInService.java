package tat.itis.chatsem.services;

import tat.itis.chatsem.dto.AccountDto;
import tat.itis.chatsem.dto.SignInForm;

public interface SignInService {
    AccountDto signIn(SignInForm signInForm);
}
