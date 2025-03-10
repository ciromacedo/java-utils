package org.macedo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;

@Getter
@Setter
public class DaoException extends DataAccessException {

    private static MessageSource messageSource;
    private final String messageKey;
    private final Object[] args;

    public DaoException(String messageKey, Object[] args, Throwable cause) {
        super(resolveMessage(messageKey, args), cause);
        this.messageKey = messageKey;
        this.args = args;
    }

    public DaoException(String messageKey, Throwable cause) {
        super(resolveMessage(messageKey), cause);
        this.messageKey = messageKey;
        this.args = null;
    }

    public DaoException(String messageKey, Object... args) {
        super(resolveMessage(messageKey, args));
        this.messageKey = messageKey;
        this.args = args;
    }

    private static String resolveMessage(String messageKey, Object... args) {
        if (messageSource != null) {
            return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
        }
        return messageKey;
    }

    public static void setMessageSource(MessageSource source) {
        messageSource = source;
    }


}
