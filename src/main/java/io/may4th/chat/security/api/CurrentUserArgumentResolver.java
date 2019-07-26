package io.may4th.chat.security.api;

import io.may4th.chat.security.impl.UserDetailsRequestHolder;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@AllArgsConstructor
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private final UserDetailsRequestHolder userDetailsRequestHolder;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        val userDetails = userDetailsRequestHolder.getUserDetails().orElse(null);
        return parameter.getParameterType().isInstance(userDetails) ?
            userDetailsRequestHolder.getUserDetails().orElse(null) :
            null;
    }
}
