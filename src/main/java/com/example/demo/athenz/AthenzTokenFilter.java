package com.example.demo.athenz;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AthenzTokenFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("X-Athenz-Principal-Auth");
		if (token != null && !token.isEmpty()) {
			AthenzToken cookieToken = new AthenzToken(token);
			SecurityContextHolder.getContext().setAuthentication(cookieToken);
		}
		filterChain.doFilter(request, response);

	}
}
