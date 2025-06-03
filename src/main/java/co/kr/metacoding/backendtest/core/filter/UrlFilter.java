package co.kr.metacoding.backendtest.core.filter;

import co.kr.metacoding.backendtest.core.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class UrlFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        String path = uri + (queryString != null ? "?" + queryString : "");

        String filteredPath = path.replaceAll("[a-zA-Z0-9/?&=:]", "");
        if (!filteredPath.isEmpty()) {
            exResponse(response, "허용되지 않은 특수문자가 URL에 포함되어 있습니다.");
            return;
        }

        chain.doFilter(req, resp);
    }

    private void exResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(400);
        PrintWriter out = response.getWriter();

        ResponseEntity resp = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(msg));

        String responseBody = new ObjectMapper().writeValueAsString(resp);
        out.println(responseBody);
    }
}

