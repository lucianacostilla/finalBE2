package com.example.clinica.service;
import org.springframework.stereotype.Service;
import com.example.clinica.entities.Usuario;
import com.example.clinica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
 private UsuarioRepository usuarioRepository;
 @Autowired
 public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
    }
 else {
        throw  new UsernameNotFoundException("Error. Usuario no encontrado. Intente de nuevo.");
    }
}


}
