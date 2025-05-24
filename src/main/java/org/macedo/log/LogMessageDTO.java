package org.macedo.log;

import java.time.LocalDateTime;

public class LogMessageDTO {

    private String detalhe;             // até 20.000 caracteres
    private LocalDateTime dataRegistro;
    private LogLevel level;
    private String service;
    private LogType tipo;

    public LogMessageDTO() {
        this.dataRegistro = LocalDateTime.now();
    }

    public LogMessageDTO(String detalhe, LogLevel level, String service, LogType tipo) {
        this.detalhe = detalhe;
        this.dataRegistro = LocalDateTime.now();
        this.level = level;
        this.service = service;
        this.tipo = tipo;
    }

    // Getters e Setters

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LogType getTipo() {
        return tipo;
    }

    public void setTipo(LogType tipo) {
        this.tipo = tipo;
    }
}
