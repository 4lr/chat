package io.may4th.chat.security.api;

import io.may4th.chat.security.api.exceptions.AccessDeniedException;
import io.may4th.chat.security.api.exceptions.AuthenticationException;
import io.may4th.chat.security.impl.UserDetailsRequestHolder;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@AllArgsConstructor
@Component
public class SecurityHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private final UserDetailsRequestHolder userDetailsRequestHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        val handlerMethod = (HandlerMethod) handler;

        val classLevelSecure = handlerMethod.getBeanType().getAnnotation(Secured.class);
        if (classLevelSecure != null) {
            check(classLevelSecure);
        }

        val methodLevelSecure = handlerMethod.getMethodAnnotation(Secured.class);
        if (methodLevelSecure != null) {
            check(methodLevelSecure);
        }

        return true;
    }

    private void check(Secured secured) {
        val userDetails = userDetailsRequestHolder.getUserDetails();
        if (secured.value().length == 0 && userDetails.isPresent()) {
            return;
        }

        val requiredAuthorities = Arrays.asList(secured.value());
        if (!userDetails
            .orElseThrow(AuthenticationException::new)
            .getAuthorities()
            .stream()
            .anyMatch(grantedAuthority -> requiredAuthorities.contains(grantedAuthority.getAuthority()))
        ) {
            throw new AccessDeniedException();
        }
    }
}
