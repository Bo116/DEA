package programa;

import java.util.ArrayList;

public class Egile {
	private String id;
	private String izena;
	private ArrayList<String> argitalpenak;
	
	public Egile(String id, String izena) {
		this.id = id;
		this.izena = izena;
		this.argitalpenak = new ArrayList<String>();
	}
	
	public String getId() {
		return id;
	}
	public void gehituArgitalpen(String a) {
		this.argitalpenak.add(a);
	}
	public ArrayList printArgitalpenak() {
		System.out.println("Egilearen id: " + this.id);
		for (int i = 0; i < this.argitalpenak.size(); i++) {
			if (this.argitalpenak.get(i) != null) {
			System.out.println("Argitalpena: " + this.argitalpenak.get(i));
		}
		}
		return this.argitalpenak;
	}
	public ArrayList<String> getArgitalpenak() {
		return argitalpenak;
	}
	public boolean argitalpenaDauka(String argitalpenId) {
		
		return this.argitalpenak.contains(argitalpenId);
	}
	public void argitalpenaKendu(String argitalpenId) {
		 this.argitalpenak.remove(argitalpenId);
	}
	
	public String getIzena() {
		return izena;
	}
	
	public int compareTo(Egile lag) {
		
		return this.id.compareTo(lag.id);
	}
}
