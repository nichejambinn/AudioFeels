package ca.moodyjay.audio.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException)
			throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			// should be using Logger for this
			System.out.println(auth.getName()
					+ " was trying to access protected resource "
					+ request.getRequestURI());
		}
		
		response.sendRedirect(request.getContextPath() + "/access-denied");
	}

}
