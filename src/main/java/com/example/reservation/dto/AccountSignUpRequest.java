package com.example.reservation.dto;

import com.example.reservation.entity.Account;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountSignUpRequest {

    private String usrId;
    private String grId;
    private String usrPw;
    private String usrNm;
    private String usrType;

    @Builder
    public AccountSignUpRequest(String usrId, String grId, String usrPw, String usrNm, String usrType) {
        this.usrId = usrId;
        this.grId = grId;
        this.usrPw = usrPw;
        this.usrNm = usrNm;
        this.usrType = usrType;
    }

    public AccountSignUpRequest(Claims claims) {
        this.usrId = claims.get("id", String.class);
    }

    public Account toEntity() {
        return Account.builder().
                usrId(usrId)
                .grId(grId)
                .usrNm(usrNm)
                .usrType(usrType)
                .build();
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
