package it.polito.tdp.alien.model;

import java.util.ArrayList;
import java.util.List;

public class Dizionario {

	private List<Parola> traduzioni;

	public Dizionario() {
		super();
		this.traduzioni = new ArrayList<Parola>();
	}
	
	public void elimina() {
		traduzioni.clear();
	}
	
	public void aggiungi(String a, String t) {
		
		Parola p = new Parola(a);
		
		if(this.traduzioni.contains(p)) {
			traduzioni.get(traduzioni.indexOf(p)).setTraduzione(t);
			return;
		}
		
		p.setTraduzione(t);
		this.traduzioni.add(p);
	}
	
	public String traduci(String a) {
		
		Parola p = new Parola(a);
		
		if(this.traduzioni.contains(p)) {
			return traduzioni.get(traduzioni.indexOf(p)).getTraduzione();
		}
		
		return null;
		
	}

	public String traduciWordWildCard(String alieno) {

		alieno = alieno.replaceAll("\\?", ".");
		
		int contatore = 0;
		
		StringBuilder sb = new StringBuilder();
		
		for(Parola p : traduzioni) {
			
			if(p.compareWild(alieno)) {
				
				contatore++;
				sb.append(p.getTraduzione()+"\n");
			}
		}
		
		if(contatore !=0) {
			return sb.toString();
		}else {
			return null;
		}
	}
	
	
	
	
}
