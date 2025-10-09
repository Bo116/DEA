package programa;

import java.util.ArrayList;

public class Argitalpen implements Comparable<Argitalpen> {
	private String id;
	private String izenburua;
	private ArrayList<String> aipamenak;
	private ArrayList<String> egileak;
	public Argitalpen(String id, String izenburua) {
		this.id = id;
		this.izenburua = izenburua;
		this.aipamenak = new ArrayList<String>();
		this.egileak = new ArrayList<String>();
	}
	
	public String getId() {
		return id;
	}
	public ArrayList<String> getAipamenak() {
		return aipamenak;
	}
	public ArrayList<String> getEgileak() {
		return egileak;
	}
	public boolean egileaDauka(String egileId) {
		
		return this.egileak.contains(egileId);
	}
	public void gehituAipamen(String aipamenId) {
		this.aipamenak.add(aipamenId);
		System.out.println("Aipamena gehitu: " + aipamenId + " argitalpenari: " + this.id);
	}
	public void gehituEgilea(String egileId) {
		this.egileak.add(egileId);
		System.out.println("Egilea gehitu: " + egileId + " argitalpenari: " + this.id);
	}
	public void egileaKendu(String egileId) {
		this.egileak.remove(egileId);
		System.out.println("Egilea kendu: " + egileId + " argitalpenari: " + this.id);
	}

	public int compareTo(Argitalpen lag) {
			return this.izenburua.compareTo(lag.izenburua);
		
	}
	public String getIzenburua() {
		return izenburua;
	}
	public void printAipamenak() {
		System.out.println("Argitalpenaren id: " + this.id);
		for (int i = 0; i < this.aipamenak.size(); i++) {
			if (this.aipamenak.get(i) != null) {
			System.out.println("Aipamena: " + this.aipamenak.get(i));
		}
		}
	}
	public void printEgileak() {
		System.out.println("Argitalpenaren id: " + this.id);
		for (int i = 0; i < this.egileak.size(); i++) {
			if (this.egileak.get(i) != null) {
			System.out.println("Egilea: " + this.egileak.get(i));
		}
		}
	}
}
