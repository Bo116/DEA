package programa;

import java.util.ArrayList;
import java.util.HashMap;

public class EgileZerrenda {
  private HashMap<String, Egile> egileak;
private static EgileZerrenda nEgileZerrenda = null;
	
	private EgileZerrenda() {
		egileak = new HashMap<String, Egile>();
	}
	public HashMap<String, Egile> getEgileak() {
		return egileak;
	}
	public static EgileZerrenda getEgileZerrenda() {
		if (nEgileZerrenda == null) {
			nEgileZerrenda = new EgileZerrenda();
		}
		return nEgileZerrenda;
	}
	public void addEgile(String id, String izena) {
		Egile e = new Egile(id.trim(), izena.trim());
		this.egileak.put(id, e);
	}
	public void kenduEgilea(String egileId) {
		Egile e = bilatu(egileId);
		if (e != null) {
			this.egileak.remove(egileId);
			
		}
	}
	public Egile bilatu(String id) {
		Egile e = this.egileak.get(id);
		if (e == null) {
			System.out.println("Ez da aurkitu egilerik id honekin: " + id);
		}
		return e;}
	
}
