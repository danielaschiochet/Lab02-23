package it.polito.tdp.alien.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parola {
	
	private String alien;
	private List<String> traduzioni;
	

	public Parola(String alien, String traduzione) {
		this.alien = alien;
		this.traduzioni = new ArrayList<String>();
		this.traduzioni.add(traduzione);
	}

	public Parola(String alien) {
		this.alien = alien;
		this.traduzioni = new ArrayList<String>();
	}
	
		
	public String getAlien() {
		return alien;
	}

	public String getTraduzione() {
		String s = "";
		
		for(String t: this.traduzioni) {
			s+=t+"\n";
		}
		
		return s;
	}

	public void setTraduzione(String traduzione) {
		traduzioni.add(traduzione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alien, traduzioni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parola other = (Parola) obj;
		return Objects.equals(alien, other.alien) && Objects.equals(traduzioni, other.traduzioni);
	}



	@Override
	public String toString() {
		return "Parola [alien=" + alien + ", traduzioni=" + traduzioni + "]";
	}

	public boolean compareWild(String alien2) {
		
		if(alien.matches(alien2))
			return true;
		else{
			return false;
		}
	}
	
	

}
