package com.gs3tenlogia.backend.backend_gs3.resources;

import com.gs3tenlogia.backend.backend_gs3.domain.Usuario;
import com.gs3tenlogia.backend.backend_gs3.dto.cadastro.CadastroNewDTO;
import com.gs3tenlogia.backend.backend_gs3.service.CadastroService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/cadastro-resource")
public class CadastroResource {

  @Autowired
  private CadastroService service;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(
    @Valid @RequestBody CadastroNewDTO objDto
  ) {
    Usuario obj = service.fromDTO(objDto);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(obj.getId())
      .toUri();
    return ResponseEntity.created(uri).build();
  }
}
