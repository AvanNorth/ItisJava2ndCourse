package tat.itis.chatsem.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tat.itis.chatsem.dto.AccountDto;
import tat.itis.chatsem.dto.SignUpForm;
import tat.itis.chatsem.models.Account;

@RequiredArgsConstructor
@Component
public class AccountsMapper {

    private final PasswordEncoder passwordEncoder;

    public AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .build();
    }

    public Account toAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .email(accountDto.getEmail())
                .build();
    }

    public Account toAccount(SignUpForm signUpForm) {
        return Account.builder()
                .role(Account.Role.USER)
                .state(Account.State.CONFIRMED)
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
    }
}
