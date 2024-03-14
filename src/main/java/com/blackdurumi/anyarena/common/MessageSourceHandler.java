package com.blackdurumi.anyarena.common;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSourceHandler {

    private static final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    public static String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public static String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}
