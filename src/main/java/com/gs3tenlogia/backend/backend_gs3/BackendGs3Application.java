package com.gs3tenlogia.backend.backend_gs3;

import com.gs3tenlogia.backend.backend_gs3.domain.Email;
import com.gs3tenlogia.backend.backend_gs3.domain.Endereco;
import com.gs3tenlogia.backend.backend_gs3.domain.Telefone;
import com.gs3tenlogia.backend.backend_gs3.domain.Usuario;
import com.gs3tenlogia.backend.backend_gs3.enums.Perfil;
import com.gs3tenlogia.backend.backend_gs3.repositories.UsuarioRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendGs3Application implements CommandLineRunner {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private BCryptPasswordEncoder pe;

  public static void main(String[] args) {
    SpringApplication.run(BackendGs3Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Endereco endereco = new Endereco(
      null,
      "71010057",
      "logradouro",
      "bairro",
      "cidade",
      "UF",
      "complemento",
      null
    );

    Usuario admin = new Usuario(
      null,
      "admin",
      "0230064132",
      Arrays.asList(
        new Telefone(null, "61982646"),
        new Telefone(null, "6121846")
      ),
      Arrays.asList(
        new Email(null, "fulanodeTal@email.com"),
        new Email(null, "fufulalanono@email.com")
      ),
      endereco,
      pe.encode("123456")
    );
    admin.addPerfil(Perfil.ADMIN);

    Usuario comum = new Usuario(
      null,
      "comum",
      "0230064132",
      Arrays.asList(
        new Telefone(null, "61982646"),
        new Telefone(null, "6121846")
      ),
      Arrays.asList(
        new Email(null, "fulanodeTal@email.com"),
        new Email(null, "fufulalanono@email.com")
      ),
      endereco,
      pe.encode("123456")
    );
    comum.addPerfil(Perfil.CLIENTE);

    usuarioRepository.saveAll(Arrays.asList(comum, admin));
  }
}
