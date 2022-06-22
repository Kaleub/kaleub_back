package com.photory.controller.auth;

import com.photory.controller.auth.dto.request.*;
import com.photory.common.dto.ResponseDto;
import com.photory.controller.auth.dto.response.SigninUserResponse;
import com.photory.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;
  
    //이메일이 유효한거 확인 되면, 인증 버튼 누를 수 있음
    @PostMapping("/signup/email/check")
    public ResponseEntity<ResponseDto> validateEmail(
            @RequestBody @Valid ValidateEmailRequestDto validateEmailRequestDto
    ) {
        authService.validateEmail(validateEmailRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("중복 체크 완료")
                        .data(null)
                        .build()
        );
    }

    //이메일 인증 실행
    @PostMapping("/signup/email")
    public ResponseEntity<ResponseDto> authEmail(
            @RequestBody @Valid AuthEmailRequestDto authEmailRequestDto
    ) {
        authService.authEmail(authEmailRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이메일 전송 성공")
                        .data(null)
                        .build()
        );
    }

    @PostMapping("/signup/email/complete")
    public ResponseEntity<ResponseDto> authEmailComplete(
            @RequestBody @Valid AuthEmailCompleteRequestDto authEmailRequestDto
    ) {
        authService.authEmailComplete(authEmailRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이메일 인증 성공")
                        .data(null)
                        .build()
        );
    }


    //마지막으로 회원 생성
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> createUser(
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto
    ) {
        authService.createUser(createUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("회원가입 성공")
                       .data(null)
                        .build()
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signinUser(
            @RequestBody SigninUserRequestDto signinUserRequestDto
    ) {
        SigninUserResponse signinUserResponse = authService.signinUser(signinUserRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 성공")
                        .data(signinUserResponse)
                        .build()
        );
    }

    @GetMapping("/check")
    ResponseEntity<ResponseDto> checkToken() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 유지")
                        .data(null)
                        .build()
        );
    }
}
