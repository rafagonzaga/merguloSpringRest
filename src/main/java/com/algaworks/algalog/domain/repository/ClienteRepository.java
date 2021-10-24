package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository // Define que ClienteRepository é um componente do Spring. 
// É um tipo onde as instâncias são gerenciadas pelo proprio container do spring. Com isso
// podemos injetar uma instância desse repositorio ClienteRepository de uma forma muito fácil
// em objetos de outras classes de nosso projeto, usando injeção de dependência.
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByNome(String nome); // findByNome -> é um padrão para a consulta. 
	// By é um delimitador e Nome é uma expressão/propriedade
	List<Cliente> findByNomeContaining(String nome); // Containing -> igual ao LIKE do MySQL
}
