package br.com.fiap.foodlivery.foodlivery.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
public class Endereco {

    String rua;
    String bairro;
    String cidade;
    String estado;
    String cep;
}
