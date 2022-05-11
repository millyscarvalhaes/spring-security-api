package com.springsecurity.security.filter;

import com.springsecurity.security.SecurityConstants;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidatorFilter implements Filter {

    public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private Charset credentialsCharset = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String header = req.getHeader(SecurityConstants.JWT_HEADER);

        if(header != null) {
            header = header.trim();

            if(StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)){

                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;

                try{
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, this.credentialsCharset);

                    int delimiter = token.indexOf(":");

                    // Delimiter does not exists
                    if(delimiter == -1){
                        throw new BadCredentialsException("Invalid basic authentication token");
                    }

                } catch (IllegalArgumentException e){
                    throw new BadCredentialsException("Failed to decode basic authentication token!");
                }

            }
        }

        chain.doFilter(request, response);


    }
}
