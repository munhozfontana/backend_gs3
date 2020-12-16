package com.gs3tenlogia.backend.backend_gs3.dto.cadastro;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import com.gs3tenlogia.backend.backend_gs3.domain.Endereco;

import org.hibernate.validator.constraints.Length;

public class CadastroNewDTO {

    @NotBlank(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracters")
    private String nome;

    @NotBlank(message = "Preenchimento Obrigatório")
    private String cpf;

    private List<String> telefone = new ArrayList<>();
    private List<String> email = new ArrayList<>();

    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<String> telefone) {
        this.telefone = telefone;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
