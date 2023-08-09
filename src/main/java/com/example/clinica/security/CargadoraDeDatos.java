package com.example.clinica.security;

import com.example.clinica.entities.Usuario;
import com.example.clinica.entities.UsuarioRol;
import com.example.clinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passACifrar= "12345";
        String passCifrada = cifrador.encode(passACifrar);
        Usuario usuarioAInsertar = new Usuario("Jorgito", "Pereyra", "jorgito@dh.com",passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);

        String passACifrar2 = "67890";
        String passCifrada2 = cifrador.encode(passACifrar2);
        Usuario usuarioAInsertar2 = new Usuario("Luciana", "Costilla", "luciana@dh.com", passCifrada2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);
    }
}
