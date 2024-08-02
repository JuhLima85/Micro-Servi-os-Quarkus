package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private String nome;

    private String descricao;

    private  String categoria;

    private  String modelo;

    private BigDecimal preco;
}
