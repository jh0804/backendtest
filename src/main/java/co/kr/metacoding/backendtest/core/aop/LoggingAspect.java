package co.kr.metacoding.backendtest.core.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final HttpServletRequest request;

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void logRequestAdvice() {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/users")) return;

        String userAgent = request.getHeader("User-Agent");
        userAgent = userAgent == null ? "" : userAgent;
        String msg = "[로그] User-Agent: ${ua}"
                .replace("${ua}", userAgent);

        log.info(msg);
    }
}
