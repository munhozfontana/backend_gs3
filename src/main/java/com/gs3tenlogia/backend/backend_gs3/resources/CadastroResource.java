package com.gs3tenlogia.backend.backend_gs3.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cadastro-resource")
public class CadastroResource {
    
    @RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<String> find() {
		return ResponseEntity.ok().body("Teste End Point");
	}


}
