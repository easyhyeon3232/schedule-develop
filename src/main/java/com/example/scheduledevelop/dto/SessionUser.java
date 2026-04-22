package com.example.scheduledevelop.dto;

import lombok.Getter;

// session에 저장할 데이터들
@Getter
public class SessionUser {

    private final Long id;
    private final String email;

    public SessionUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }

}
