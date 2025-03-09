package org.macedo.service;

import org.macedo.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class BaseService<T extends BaseEntity> {

    protected final ModelMapper modelMapper;
    protected final MessageSource messageSource;

    protected BaseService(ModelMapper modelMapper, MessageSource messageSource) {
        this.modelMapper = modelMapper;
        this.messageSource = messageSource;
    }

    protected abstract void beforeInsert(T bean);
    protected abstract void beforeUpdate(T bean);

    protected Locale getLocale(){
        return LocaleContextHolder.getLocale();
    }

    protected String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, getLocale());
    }
}
