package com.gs3tenlogia.backend.backend_gs3.config;

import com.gs3tenlogia.backend.backend_gs3.security.JWTAuthenticationFilter;
import com.gs3tenlogia.backend.backend_gs3.security.JWTAuthorizationFilter;
import com.gs3tenlogia.backend.backend_gs3.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  private static final String[] PUBLIC_MATCHERS_POST = {
    "/cadastro-resource/**",
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable();
    http
      .authorizeRequests()
      .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
      .authenticated();

    http.addFilter(
      new JWTAuthenticationFilter(authenticationManager(), jwtUtil)
    );

    http.addFilter(
      new JWTAuthorizationFilter(
        authenticationManager(),
        jwtUtil,
        userDetailsService
      )
    );
    
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(
      "/**",
      new CorsConfiguration().applyPermitDefaultValues()
    );
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(bCryptPasswordEncoder());
  }
}