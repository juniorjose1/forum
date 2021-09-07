package br.com.alexandre.forumoficial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.forumoficial.config.security.TokenService;
import br.com.alexandre.forumoficial.controller.dto.TokenDto;
import br.com.alexandre.forumoficial.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm loginForm){
		UsernamePasswordAuthenticationToken user = 
				new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getSenha());
		Authentication auth = authManager.authenticate(user);
		String token = tokenService.gerarToken(auth);
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}

}
