package fr.eni.tp.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;

public class Participant extends Personne{
	
	
	public Participant(long id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}
	
	public Participant(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}

}
