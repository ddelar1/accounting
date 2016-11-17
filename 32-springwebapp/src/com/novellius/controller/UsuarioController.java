package com.novellius.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novellius.pojo.Usuario;
import com.novellius.pojo.valid.SpringFormGroup;
import com.novellius.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/usuario")
	public String showForm(Model model){
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarioService.findAll());
		return "usuario";
	}
	
	
	@RequestMapping("/usuario/save")
	public String register(@ModelAttribute("usuario")  @Validated(value=SpringFormGroup.class) Usuario usuario,
			BindingResult result,
			Model model){
		if (result.hasErrors()) {
			return "usuario";
		}
		
		usuarioService.seve(usuario);
		
		return "redirect:/usuario";
	}

	
}
