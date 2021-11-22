package com.cast.spring1.spring1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cast.spring1.spring1.model.Usuario;
import com.cast.spring1.spring1.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping(value = "/usuario/{nome}/idade/{idade}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String salvarUsuario(@PathVariable String nome, @PathVariable int idade) {

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		
		usuario.setIdade(idade);
		usuarioRepository.save(usuario);
 
		return "Salvo no banco";
	}
	
	@GetMapping(value = "listar")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
        List<Usuario> usuario = usuarioRepository.findAll();
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); //retorna lista em Json
    }
	
//	@DeleteMapping(value = "delete")
//    @ResponseBody
//    public ResponseEntity<String> delete(@RequestParam Long id){
//        usuarioRepository.deleteById(id);
//        return new ResponseEntity<String>("Usuario " + id + " deletado", HttpStatus.OK);
//    }
	
	@RequestMapping(value = "usuario/deletar/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);

        return "<h3>Usuario deletado. </h3>";
    }
	
	@RequestMapping(value = "/usuario/buscar/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<Usuario>> buscarUsuario(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return new ResponseEntity <Optional <Usuario>> (usuario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "usuario/updt/{id}/{nome}/{idade}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public String updtUser(@PathVariable String nome, @PathVariable int idade) {

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		
		usuario.setIdade(idade);
		usuarioRepository.save(usuario);
 
		return "Usuario atualizado";
	}
	
}
