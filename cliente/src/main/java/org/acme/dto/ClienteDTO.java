package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Long idade;
}
