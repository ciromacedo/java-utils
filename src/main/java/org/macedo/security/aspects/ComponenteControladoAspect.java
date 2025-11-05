package org.macedo.security.aspects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.macedo.security.JwtUtil;
import org.macedo.security.annotations.ComponenteControlado;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ComponenteControladoAspect {

    private final JwtUtil jwtUtil;

    @Around("@annotation(componenteControlado)")
    public Object verificarAutorizacao(ProceedingJoinPoint joinPoint, ComponenteControlado componenteControlado) throws Throwable {

        String identificador = componenteControlado.identificador();

        // Captura o token JWT atual (do contexto de segurança)
        String token = jwtUtil.getTokenFromContext();

        if (Objects.isNull(token)) {
            log.warn("Acesso negado — nenhum token JWT encontrado no contexto");
            throw new AccessDeniedException("Acesso negado: usuário não autenticado");
        }

        // Verifica se o token contém o identificador do componente
        boolean autorizado = jwtUtil.tokenPossuiPermissao(token, identificador);

        if (!autorizado) {
            log.warn("Acesso negado — componente requerido [{}] não presente no JWT", identificador);
            throw new AccessDeniedException("Acesso negado: permissão insuficiente");
        }

        return joinPoint.proceed();
    }
}
