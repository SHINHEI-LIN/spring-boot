package com.shl.springbootquick.thymeleaf.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            return new Locale(split[0],split[1]);
        }
        String sysLanguage = httpServletRequest.getHeader("accept-language");
        // en,zh-CN;q=0.9,zh;q=0.8,en-US;q=0.7,ja;q=0.6
        // todo 需适配IE
        if (!StringUtils.isEmpty(sysLanguage)) {
            String[] split = sysLanguage.split(";");
            //en,zh-CN
            String string[] = split[0].split(",");
            String string1[] = string[0].split("-");
            return new Locale(string[1], string1[1]);
        }

        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
