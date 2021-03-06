package com.gs3tenlogia.backend.backend_gs3.dto.cadastro;

import com.gs3tenlogia.backend.backend_gs3.domain.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class CadastroNewDTO {

  @NotBlank(message = "Preenchimento Obrigatório")
  @Length(
    min = 3,
    max = 100,
    message = "O tamanho deve ser entre 3 e 100 caracters"
  )
  private String nome;

  @NotBlank(message = "Preenchimento Obrigatório")
  private String cpf;

  private List<String> telefone = new ArrayList<>();
  private List<String> email = new ArrayList<>();

  private Endereco endereco;

  @NotEmpty(message = "Preenchimento obrigatório")
  private String senha;

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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
