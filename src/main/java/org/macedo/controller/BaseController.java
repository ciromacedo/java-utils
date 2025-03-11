package org.macedo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class BaseController<T> {

    /**
     * Retorna uma resposta de sucesso com status 200 (OK).
     */
    protected ResponseEntity<T> responderOk(T body) {
        return ResponseEntity.ok(body);
    }

    /**
     * Retorna uma resposta de criação com status 201 (Created).
     */
    protected ResponseEntity<T> responderCriado(String path, Long id, T body, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path(path).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(body);
    }

    /**
     * Retorna uma resposta sem conteúdo (204 - No Content).
     */
    protected ResponseEntity<Void> responderNoContent() {
        return ResponseEntity.noContent().build();
    }
}
