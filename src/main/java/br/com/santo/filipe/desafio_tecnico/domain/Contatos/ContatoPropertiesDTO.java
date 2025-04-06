package br.com.santo.filipe.desafio_tecnico.domain.Contatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContatoPropertiesDTO {

    private String email;
    private String firstname;
    private String lastname;
    private String website;
    private String company;
    private String phone;

}
