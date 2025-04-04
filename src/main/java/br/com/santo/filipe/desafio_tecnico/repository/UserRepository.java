package br.com.santo.filipe.desafio_tecnico.repository;

import br.com.santo.filipe.desafio_tecnico.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, String> {

    UserDetails findByLogin(String login);
}
