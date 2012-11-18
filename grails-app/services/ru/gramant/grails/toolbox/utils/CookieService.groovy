/*
 * CookieService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

package ru.gramant.grails.toolbox.utils

import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.Cookie
import org.codehaus.groovy.grails.commons.ConfigurationHolder

/**
 * Service used to manipulate cookies
 */
class CookieService {

    boolean transactional = false

    /** Configure cookie default domain property */
    private static String domain = ConfigurationHolder.config.cookie.domain

    /**
     * Gets the value of the named cookie.  Returns null if does not exist
     */
    String get(String name) {
        Cookie cookie = getCookie(name)
        if (cookie) {
            def value = cookie.getValue()
            return value
        }
        else {
            return null
        }
    }

    /**
     * @return decoded value of encoded cookie with name {@code name}
     */
    String getAndDecode(String name) {
        return get(name)?.decodeCookie()
    }

    /**
     * Sets the cookie with {@code name} to {@code value}, with {@code maxAge} in seconds
     */
    void set(request, response, name, value, maxAge) {
        setCookie(request, response, name.toString(), value.toString(), maxAge)
    }

    /**
     * Sets cookie with {@code name} to {@code value}, max age = Integer.MAX_VALUE
     */
    void set(request, response, name, value) {
        set(request, response, name, value, Integer.MAX_VALUE)
    }

    /**
     * set cookie value and encode it (non ASCI symbols)
     * http://stackoverflow.com/questions/9109318/java-lang-illegalargumentexception-control-character-in-cookie-value-or-attribu
     */
    void setAndEncode(request, response, name, String value, maxAge=Integer.MAX_VALUE) {
        set(request, response, name, value.encodeAsCookie(), maxAge)
    }

    /**
     * Deletes cookie with {@code name}.
     */
    void delete(request, response, name) {
        setCookie(request, response, name.toString(), null, 0)
    }

    /** ***********************************************************/
    private void setCookie(request, response, name, value, maxAge) {
        setCookie(request, response, name.toString(), value.toString(), maxAge, domain)
    }

    /**
     * Saves cookie with {@code name}, {@code value}, {@code maxAge} and {@code domain} to {@code response} objects
     * If domain is set, creates additional cookie for Chrome without domain (@see https://confluence.devadmin.com/display/DA/Cookie+Service)
     */
    private void setCookie(request, response, String name, String value, maxAge, String domain) {
        Cookie cookie = new Cookie(name, value)
        cookie.setMaxAge(maxAge)
        cookie.path = '/'
        if (domain) cookie.domain = domain
        response.addCookie(cookie)

        if (domain && request?.chrome && domain?.charAt(0) == '.') {
            cookie = new Cookie(name, value)
            cookie.setMaxAge(-1) //expires when browser is closed
            cookie.path = '/'
            response.addCookie(cookie)
        }
    }

    /** ***********************************************************/
    private Cookie getCookie(name) {
        def cookies = RequestContextHolder.currentRequestAttributes().request.getCookies()
        if (cookies == null || name == null || name.length() == 0) {
            return null
        }
        // Otherwise, we have to do a linear scan for the cookie.
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                return cookies[i]
            }
        }
        return null;
    }
}
