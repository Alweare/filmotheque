package fr.eni.tp.filmotheque.bo;

import java.util.Objects;

public class Avis {
	private long id;
	private int note;
	private String commentaire;
	private Membre membre;
	
	public Avis(long id, int note, String commentaire, Membre membre) {
		this(note, commentaire);
		this.id = id;
		this.setMembre(membre);
		
	}

	public Avis(int note, String commentaire) {
		this.note = note;
		this.commentaire = commentaire;
	}

	public Avis() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	@Override
	public String toString() {
		return "Avis [id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", membre=" + membre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avis other = (Avis) obj;
		return id == other.id;
	}
	
	
	
	
	

}
