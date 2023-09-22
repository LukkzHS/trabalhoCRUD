package br.edu.iftm.tspi.cadastro.dto;

import lombok.Data;

@Data
public class CadastroDTO {
    
    private static Long idCounter = 1L;

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public CadastroDTO() {
        this.id = idCounter++;
    }
}
