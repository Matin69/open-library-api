package com.openlibrary.app.security;

import com.openlibrary.app.user.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ResourceOwnerInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    public ResourceOwnerInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isUser(authentication)) {
            Long requestUserId = extractRequestUserId(request);
            if (requestUserId != null) {
                userRepository.findByName(authentication.getName())
                        .ifPresentOrElse(
                                user -> {
                                    if (!user.getId().equals(requestUserId))
                                        throw new AccessDeniedException("User id is not match with request user id");
                                },
                                () -> {
                                    throw new IllegalStateException("Auth principal not exists in repository");
                                }
                        );
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean isUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .allMatch(authority -> authority.equals("ROLE_USER"));
    }

    private Long extractRequestUserId(HttpServletRequest request) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables != null) {
            String userIdPathVariable = pathVariables.get("userId");
            if (userIdPathVariable != null)
                return Long.valueOf(userIdPathVariable);
        }
        return null;
    }
}
