package fr.eni.tp.filmotheque.bll.contexte;


import org.springframework.stereotype.Service;

import fr.eni.tp.filmotheque.bo.Membre;


@Service


public interface ContexteService  {
	
	Membre charger(String email);

}
