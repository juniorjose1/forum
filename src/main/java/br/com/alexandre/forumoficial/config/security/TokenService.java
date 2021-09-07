package br.com.alexandre.forumoficial.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alexandre.forumoficial.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	public String gerarToken(Authentication auth) {
		Date dataAtual = new Date();
		Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));
		Usuario userLogado = (Usuario) auth.getPrincipal();
		return Jwts.builder()
					.setIssuer("API do Fórum Oficial")
					.setSubject(userLogado.getId().toString())
					.setIssuedAt(dataAtual)
					.setExpiration(dataExpiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}

	public Boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims bodyToken = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(bodyToken.getSubject());
	}

}
