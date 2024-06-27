package br.com.fiap.loja.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    private ClienteRepository repository;


    
    @GetMapping
    public Page<Cliente> listarTodos(
        Pageable pageable, 
        @RequestParam(required = false) String nome) {
        
        if (nome == null) return repository.findAll(pageable);
        return repository.findByNome(nome, pageable);
    }




    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody  @Valid Cliente cliente) {
        repository.save(cliente);
        return ResponseEntity.status(201).body(cliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> remover(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
        @PathVariable Long id, 
        @RequestBody @Valid Cliente cliente) {

        cliente.setId(id);
        repository.save(cliente);
        return ResponseEntity.ok(cliente);

    }



    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizarParcial(
        @PathVariable Long id, 
        @RequestBody @Valid Cliente cliente) {

        Cliente clienteAtual = repository.findById(id).get();
        
        var nome = cliente.getNome();
        if (nome == null) {
            nome = clienteAtual.getNome();
        }

        var email = cliente.getEmail();
        if (email == null) {
            email = clienteAtual.getEmail();
        }
        
        clienteAtual.setNome(nome);
        clienteAtual.setEmail(email);
        repository.save(clienteAtual);
        return ResponseEntity.ok(clienteAtual);
    }
    


}
