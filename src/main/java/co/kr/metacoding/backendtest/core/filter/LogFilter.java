package co.kr.metacoding.backendtest.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String userAgent = request.getHeader("User-Agent");
        userAgent = userAgent == null ? "" : userAgent;

        String msg = "[로그] User-Agent: ${ua}"
                .replace("${ua}", userAgent);

        log.info(msg);

        filterChain.doFilter(req, resp);
    }
}