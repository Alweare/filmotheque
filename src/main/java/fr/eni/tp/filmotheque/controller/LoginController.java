package fr.eni.tp.filmotheque.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.filmotheque.bll.contexte.ContexteService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreDAO;

@Controller
@SessionAttributes({"membreSession"})
public class LoginController {
	

	private ContexteService contexteService;


	public LoginController(ContexteService contexteService) {
		super();
		this.contexteService = contexteService;
	}

	@GetMapping("/login")
	public String login() {
		return"login";
		
	}
	
	@GetMapping("/session")
	public String connexionSession(@ModelAttribute("membreSession") Membre membreSession,@RequestParam(name = "email", required=false, defaultValue = "jtrillard@campus-eni.fr") String email,Principal principal) {
		
		Membre membreAjour = contexteService.charger(principal.getName());
		// mise à jour du membre en session avec le membre chargé (membreAjour)
		if(membreAjour != null) {
			membreSession.setId(membreAjour.getId());
			membreSession.setNom(membreAjour.getNom());
			membreSession.setPrenom(membreAjour.getPrenom());
			membreSession.setPseudo(membreAjour.getPseudo());
			membreSession.setAdmin(membreAjour.isAdmin());
		}else {
			// mettre a jour le membre a null 
			membreSession.setId(0);
			membreSession.setNom(null);
			membreSession.setPrenom(null);
			membreSession.setPseudo(null);
			membreSession.setAdmin(false);
			
		}
		return "redirect:/";
		
		
	}
	
	@ModelAttribute("membreSession")
	public Membre chargerMembreSession() {
		
	
		return new Membre();
	}

	

}
