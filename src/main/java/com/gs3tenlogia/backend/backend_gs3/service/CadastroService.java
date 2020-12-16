package com.gs3tenlogia.backend.backend_gs3.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.gs3tenlogia.backend.backend_gs3.domain.Email;
import com.gs3tenlogia.backend.backend_gs3.domain.Telefone;
import com.gs3tenlogia.backend.backend_gs3.domain.Usuario;
import com.gs3tenlogia.backend.backend_gs3.dto.cadastro.CadastroNewDTO;
import com.gs3tenlogia.backend.backend_gs3.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository repo;

    @Transactional
    public Usuario insert(Usuario obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Usuario fromDTO(CadastroNewDTO objDto) {
        Usuario usuario = new Usuario();
        usuario.setCpf(objDto.getCpf());
        usuario.setNome(objDto.getNome());
        usuario.setEndereco(objDto.getEndereco());
        usuario.setTelefone(
                objDto.getTelefone().stream().map(item -> new Telefone(null, item)).collect(Collectors.toList()));

        usuario.setEmail(objDto.getEmail().stream().map(item -> new Email(null, item)).collect(Collectors.toList()));
        return usuario;
    }

}
