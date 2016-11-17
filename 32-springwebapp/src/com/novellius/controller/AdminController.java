package com.novellius.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.novellius.pojo.Admin;
import com.novellius.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String showAdmin(Model model,
			@ModelAttribute("resultado") String resultado) {

		List<Admin> admins = adminService.findAll();

		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		model.addAttribute("resultado", resultado);
		model.addAttribute("admins", admins);

		return "admin";
	}

	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String handleAdmin(@ModelAttribute("admin") Admin adminForm,
			Model model, RedirectAttributes ra) {

		adminService.saveOrUpdate(adminForm);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/admin";
	}

	@RequestMapping("/admin/{idAd}/update")
	public String showUpdate(Model model, @PathVariable("idAd") int id) {

		Admin admin = adminService.findById(id);
		model.addAttribute("admin",admin);
		return "admin";
	}

	@RequestMapping("/admin/{idAd}/delete")
	public String delete(@PathVariable("idAd") int idAd, RedirectAttributes ra) {

		adminService.delete(idAd);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito"); 
		return "redirect:/admin";
	}

	@RequestMapping(value="/admin/json/search", produces="application/json")
	@ResponseBody
	public Map<String,Object> findAll(@RequestParam("term") String nombreAdmin){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Admin> admins = adminService.findAllLikeNombre(nombreAdmin);
		
		for (int i = 0; i < admins.size(); i++) {
			Admin admin = admins.get(i);
			map.put("nombre"+i, admin.getIdAd() + " " + admin.getNombre());
		}
		System.out.println(map.toString());
		return map;
	}
	
	@RequestMapping(value="/admin/json/prueba", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String showPropietario(HttpServletRequest request){
		String mensaje = request.getParameter("prueba");
		System.out.println("Entro..._" + mensaje);
		return mensaje;
	}
	
	
	
}
