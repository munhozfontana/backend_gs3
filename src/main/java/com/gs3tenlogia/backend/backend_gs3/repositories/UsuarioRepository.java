package com.gs3tenlogia.backend.backend_gs3.repositories;


import javax.transaction.Transactional;

import com.gs3tenlogia.backend.backend_gs3.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  @Transactional()
  Usuario findByNome(String nome);
}
