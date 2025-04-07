package br.com.santo.filipe.desafio_tecnico.models.Contact;

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
public class ContactPropertiesDTO {

    private String email;
    private String firstname;
    private String lastname;
    private String website;
    private String company;
    private String phone;

}
