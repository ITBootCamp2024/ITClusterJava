package com.ua.itclusterjava2024.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.itclusterjava2024.dto.response.JwtErrorResponse;
import com.ua.itclusterjava2024.security.JwtService;
import com.ua.itclusterjava2024.service.implementation.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            JwtErrorResponse error = new JwtErrorResponse("Missing 'Bearer' type in 'Authorization' header. Expected 'Authorization: Bearer <JWT>'");
            sendErrorResponse(response, error);
            log.warn(error.getMsg());
            return;
        }

        String jwt = authHeader.substring(BEARER_PREFIX.length());

        try {
            String email = jwtService.extractEmail(jwt);
            if (StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userServiceImpl
                        .userDetailsService()
                        .loadUserByUsername(email);

                // Якщо токен валідний, то автентифікуємо користувача
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    SecurityContext context = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, new JwtErrorResponse("Token has expired"));
            log.warn(e.toString());
        } catch (JwtException e) {
            sendErrorResponse(response, new JwtErrorResponse("Invalid token"));
            log.warn(e.toString());
        }
    }

    private void sendErrorResponse(HttpServletResponse response, JwtErrorResponse message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(message));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
