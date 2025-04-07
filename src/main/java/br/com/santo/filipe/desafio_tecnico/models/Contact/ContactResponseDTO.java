package br.com.santo.filipe.desafio_tecnico.models.Contact;

import java.time.ZonedDateTime;

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
public class ContactResponseDTO {
    private String id;
    private ContactResponsePropertiesDTO properties;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean archived;
}