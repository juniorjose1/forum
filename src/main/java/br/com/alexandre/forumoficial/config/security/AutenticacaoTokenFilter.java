package br.com.alexandre.forumoficial.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alexandre.forumoficial.modelo.Usuario;
import br.com.alexandre.forumoficial.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository userRepository;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = obterToken(request);
		Boolean tokenValid = tokenService.isTokenValid(token);
		if(tokenValid) {
			autenticarToken(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarToken(String token) {
		Long idUser = tokenService.getUserId(token);
		Optional<Usuario> usuario = userRepository.findById(idUser);
		UsernamePasswordAuthenticationToken user = 
				new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(user);
	}
	
	private String obterToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
