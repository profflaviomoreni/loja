package br.com.fiap.loja.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    

    Page<Cliente> findByNome(String nome, Pageable pageable);
    
}
