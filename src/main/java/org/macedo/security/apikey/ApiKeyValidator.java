package org.macedo.security.apikey;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface ApiKeyValidator {

    boolean isValid(String apiKey);

    /**
     * Retorna as permissões (roles/componentes) associadas à API Key.
     * Essas authorities serão colocadas no SecurityContext.
     */
    List<GrantedAuthority> getAuthorities(String apiKey);

    /**
     * Identifica o dono da API Key (usuário ou sistema).
     * Pode ser ID, nome ou qualquer identificador.
     */
    String resolveSubject(String apiKey);
}
