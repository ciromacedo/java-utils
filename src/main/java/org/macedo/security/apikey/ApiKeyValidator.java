package org.macedo.security.apikey;

public interface ApiKeyValidator {

    boolean isValid(String apiKey);

    /**
     * Identifica o dono da API Key (usuário ou sistema).
     * Pode ser ID, nome ou qualquer identificador.
     */
    String resolveSubject(String apiKey);
}
