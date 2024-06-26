package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Film {
	private long id;
	@NotBlank(message="ne doit pas être vide")
	@Size(max=250)
	private String titre;
	@NotNull(message="il faut déclarer une année")
	@Min(value = 1900, message="l'année doit être au minimum de 1900")
	private int annee;
	@NotNull(message="la durée doit être supérieur a 0")

	@Min(value = 0)
	private int duree;
	@Size(min=20, max = 250)
	private String synopsis;
	@NotNull(message="il faut selectionner un genre")
	private Genre genre;
	private List<Avis> Avis;
	private List<Participant> acteurs;
	@NotNull(message="il faut un realisateur")
	private Participant realisateur;

	public Film() {
		this.acteurs = new ArrayList<Participant>();
		this.Avis = new ArrayList<Avis>();

	}

	public Film(String titre, int annee, int duree, String synopsis) {
		this(duree, titre, annee, duree, synopsis);
		this.Avis = new ArrayList<Avis>();
		this.acteurs = new ArrayList<Participant>();

	}
	public Film(long id, String titre, int annee, int duree, String synopsis) {
		this();
		this.id = id;
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;

	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public List<Avis> getAvis() {
		return Avis;
	}
	public void setAvis(List<Avis> avis) {
		Avis = avis;
	}
	public List<Participant> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<Participant> acteurs) {
		this.acteurs = acteurs;
	}
	public Participant getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(Participant realisateur) {
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return String.format(
				"Film (%s)%n  titre : %s, annee : %s, duree : %s%n synopsis : %s%n genre : %s%n Avis : %s%n acteurs : %s%n realisateur : %s%n",
				id, titre, annee, duree, synopsis, genre, Avis, acteurs, realisateur);
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
		Film other = (Film) obj;
		return id == other.id;
	}








}
