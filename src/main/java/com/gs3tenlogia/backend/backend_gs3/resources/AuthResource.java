package com.gs3tenlogia.backend.backend_gs3.resources;

import javax.servlet.http.HttpServletResponse;

import com.gs3tenlogia.backend.backend_gs3.security.JWTUtil;
import com.gs3tenlogia.backend.backend_gs3.security.UserSS;
import com.gs3tenlogia.backend.backend_gs3.security.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Content-Language", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}