package com.ead.course.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CourseApiSecurity {

    public Authentication gAuthentication() {

        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UUID getUsuarioId() {

        Jwt jwt = (Jwt) gAuthentication().getPrincipal();
        var strId = jwt.getClaim("usuario_id").toString();

        return UUID.fromString(strId);

    }

}
