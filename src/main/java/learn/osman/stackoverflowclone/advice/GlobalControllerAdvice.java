package learn.osman.stackoverflowclone.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("activeNav")
    public String getActiveNav(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/questions")) {
            return "questions";
        } else if (requestURI.startsWith("/tags")) {
            return "tags";
        } else if (requestURI.startsWith("/users")) {
            return "users";
        } else {
            return "home";
        }
    }
}
