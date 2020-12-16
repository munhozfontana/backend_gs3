
package com.gs3tenlogia.backend.backend_gs3.security;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}