package br.com.fiap.loja.cliente;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Data
@Entity
public class Cliente {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @NotBlank
    private String nome;

    @NotEmpty
    @Email(message = "digite um email válido!")
    private String email;


}

