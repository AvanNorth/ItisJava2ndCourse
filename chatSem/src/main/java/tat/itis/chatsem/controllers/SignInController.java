package tat.itis.chatsem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tat.itis.chatsem.dto.SignInForm;
import tat.itis.chatsem.dto.UserDto;
import tat.itis.chatsem.services.SignInService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/signIn")
public class SignInController {

    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<UserDto> signIn(@RequestBody SignInForm signInForm, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            UserDto userDto = signInService.signIn(signInForm);
            if (userDto == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }

            session.setAttribute("userId", userDto.getId());
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(userDto);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
