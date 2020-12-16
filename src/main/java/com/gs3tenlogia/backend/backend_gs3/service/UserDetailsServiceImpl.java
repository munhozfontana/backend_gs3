package com.gs3tenlogia.backend.backend_gs3.service;

import com.gs3tenlogia.backend.backend_gs3.domain.Usuario;
import com.gs3tenlogia.backend.backend_gs3.repositories.UsuarioRepository;
import com.gs3tenlogia.backend.backend_gs3.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository repo;

  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    Usuario cli = repo.findByNome(email);
    if (cli == null) {
      throw new UsernameNotFoundException(email);
    }
    return new UserSS(
      cli.getId(),
      cli.getNome(), 
      cli.getSenha(),
      cli.getPerfis()
    );
  }
}
