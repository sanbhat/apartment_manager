package com.sanbhat.aptmgr.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.sanbhat.aptmgr.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;

		final String authHeader = request.getHeader(Constants.HTTP_HEADER_AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(Constants.JWT_TOKEN_PREFIX)) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(Constants.JWT_TOKEN_PREFIX.length()); // The part after JWT_TOKEN_PREFIX

        try {
            final Claims claims = Jwts.parser().setSigningKey(Constants.JWT_KEY)
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }
        
        chain.doFilter(req, res);

	}
	
}
