package br.com.alexandre.forumoficial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.forumoficial.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<LoginForm> autenticar(@RequestBody @Valid LoginForm loginForm){
		UsernamePasswordAuthenticationToken user = 
				new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getSenha());
		authManager.authenticate(user);//AQUI VERIFICA CLASSE AUTENTICATIONSERVICE
		return ResponseEntity.ok(loginForm);
	}

}
