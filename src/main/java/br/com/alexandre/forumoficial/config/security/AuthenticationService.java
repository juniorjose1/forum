package br.com.alexandre.forumoficial.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alexandre.forumoficial.modelo.Usuario;
import br.com.alexandre.forumoficial.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if(usuario != null) {
			return usuario;
		}
		throw new UsernameNotFoundException("Dados Invalidos !");
	}
	
	public static Usuario recuperarUsuarioAutenticado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) auth.getPrincipal();
	}

}
