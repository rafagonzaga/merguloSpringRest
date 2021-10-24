package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // Representa uma entidade
// @Table(name = "tb_cliente")// Para alterar o nome da tabela
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id // Define a chave primária. Identifica a identidade.
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Diz para usar a forma nativa do BD
	private Long id;
	
	// @Column(name="nome_cliente")//Especifica o nome da coluna
	//@NotNull // Impedindo que o nome seja nulo. Não impede que seja vazio
	@NotBlank // Impede que seja nulo ou vazio
	@Size(max = 60)// Máximo de caracteres
	private String nome;
	
	@NotBlank
	@Email // Definir a sintaxe correta
	@Size(max = 255)
	private String email;
	
	@Column(name = "fone")
	@NotBlank
	@Size(max = 20)
	private String telefone;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
