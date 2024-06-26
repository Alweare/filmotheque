package fr.eni.tp.filmotheque.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> erreurs;

	public BusinessException(List<String> erreurs) {
		super();
		this.erreurs = new ArrayList<String>();
	}
	
	public void add(String message) {
		erreurs.add(message);
	}
	public boolean hasError() {
		return !erreurs.isEmpty();
	}

	public List<String> getErreurs() {
		return erreurs;
	}
	
	
	
	

}
