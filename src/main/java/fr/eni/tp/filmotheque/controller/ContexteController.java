package fr.eni.tp.filmotheque.controller;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fr.eni.tp.filmotheque.bll.contexte.ContexteService;
import fr.eni.tp.filmotheque.bo.Membre;


@Controller
@SessionAttributes({"membreSession"})
@RequestMapping("/contexte")
public class ContexteController {


	private ContexteService contexteService;


	
	public ContexteController(ContexteService contexteService) {
		this.contexteService = contexteService;
	}



	@GetMapping
	public String afficherContexte() {
	
		return "view-contexte";
	}	
	
	@GetMapping("/session")
	public String connexionSession(@ModelAttribute("membreSession") Membre membreSession,@RequestParam(name = "email", required=false, defaultValue = "jtrillard@campus-eni.fr") String email) {
		
		Membre membreAjour = contexteService.charger(email);
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
	
		
		return "redirect:/films";
	
	}
	@GetMapping("/cloture")
	public String finSession(SessionStatus sessionStatus) {
		
		//vide tous les attributs de la session
		sessionStatus.setComplete();
		
		//pour valider la destruction de la session il faut faire un redirect
		
		return "redirect:/contexte";
		
	}


	
	@ModelAttribute("membreSession")
	public Membre chargerMembreSession() {
		
	
		return new Membre();
	}
	

}
