package org.macedo.security.apikey;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface ApiKeyValidator {

    boolean isValid(String apiKey);
    List<GrantedAuthority> getAuthorities(String apiKey);
    String resolveSubject(String apiKey);
    void registrarUso(String apiKey);

}
