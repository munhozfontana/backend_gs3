package com.gs3tenlogia.backend.backend_gs3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs3tenlogia.backend.backend_gs3.enums.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;
  private String cpf;

  @OneToMany(mappedBy = "usuario")
  private List<Telefone> telefone = new ArrayList<>();

  @OneToMany(mappedBy = "usuario")
  private List<Email> email = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
  private Endereco endereco;

  @JsonIgnore
  private String senha;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "PERFIS")
  private Set<Integer> perfis = new HashSet<>();

  public Usuario(
    Integer id,
    String nome,
    String cpf,
    List<Telefone> telefone,
    List<Email> email,
    Endereco endereco,
    String senha
  ) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.endereco = endereco;
    this.senha = senha;
    addPerfil(Perfil.CLIENTE);
  }

  public Usuario() {}

  public Set<Perfil> getPerfis() {
    return perfis
      .stream()
      .map(x -> Perfil.toEnum(x))
      .collect(Collectors.toSet());
  }

  public void addPerfil(Perfil perfil) {
    perfis.add(perfil.getCod());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public List<Telefone> getTelefone() {
    return telefone;
  }

  public void setTelefone(List<Telefone> telefone) {
    this.telefone = telefone;
  }

  public List<Email> getEmail() {
    return email;
  }

  public void setEmail(List<Email> email) {
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
