package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor // Gera um construtor pelo Lombok
@RestController // Informa ao spring que a classe é um componente controlador
@RequestMapping("/clientes") // Para evitar de ter que digitar /clientes em cada GetMapping
public class ClienteController {
	
	@Autowired // define que queremos injetar uma instância que está sendo
	// gerenciada pelo Spring. Obs.: O Spring faz uma instânciação da interface "magicamente"
	private ClienteRepository clienteRepository;
	
	@GetMapping // Mapeia a requisição com o método.
	// Chegando a requisição, o método abaixo é disparado.
	public List<Cliente> listar() {
//		return clienteRepository.findByNome("João da Sival");
//		return clienteRepository.findByNomeContaining("Ma");
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")// PathVariable faz um binding entre o {clienteId} e o clienteId abaixo
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId); // findById retorna um optional, que é tipo um container que pode ou não estar vazio
//		if(cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build(); // Retorna o cliente ou null

//		Outra implementação do retorno do método
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok) // Alternativa à linha acima
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping // vincula ao método POST em /clientes
	@ResponseStatus(HttpStatus.CREATED)// Para retornar o status 201
	// RequestBody vincula o parâmetro do método ao body da requisição
	// @Valid abaixo valida a entrada de acordo com as especificações da classe Cliente.java
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente); // Já salva e retorna
	}
	
	@PutMapping("/{clienteId}") // Fazendo o binding
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId); // Necessário para forçar a atualização
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente); // Retorna OK (codigo 200)
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build(); // Retorna 204. Sucesso sem body
		
	}
	
}
