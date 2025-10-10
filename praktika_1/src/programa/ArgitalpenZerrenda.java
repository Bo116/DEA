package programa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ArgitalpenZerrenda {
	private HashMap<String, Argitalpen> argitalpenak;
	private static ArgitalpenZerrenda nArgitalpenZerrenda = null;
	private ArgitalpenZerrenda() {
		this.argitalpenak = new HashMap<String, Argitalpen>();
	}
	public static ArgitalpenZerrenda getArgitalpenZerrenda() {
		if (nArgitalpenZerrenda == null) {
			nArgitalpenZerrenda = new ArgitalpenZerrenda();
		}
		return nArgitalpenZerrenda;
	}
	public HashMap<String, Argitalpen> getArgitalpenak() {
		return argitalpenak;
	}
	public void addArgitalpen(String id, String izenburua) {
		Argitalpen a = new Argitalpen(id.trim(), izenburua.trim());
		this.argitalpenak.put(id, a);
	}
	public Argitalpen bilatu(String id) {
		Argitalpen a = this.argitalpenak.get(id);
		return a;
	}
	public void kenduArgitalpen(String id) {
		Argitalpen a = bilatu(id);
		if (a != null) {
			this.argitalpenak.remove(id);
		}
	}
	public void addEgilea(String argitalpenId, String egileId) {
		Argitalpen a = bilatu(argitalpenId);
		if (a != null) {
			a.gehituEgilea(egileId);
		}
	}
	public void addAipamen(String id, String aipamenId) {
	   
		Argitalpen a = bilatu(id);
		if (a != null) {
			a.gehituAipamen(aipamenId);
		}
	}
	public ArrayList<Argitalpen> getArgitalpenakOrdenatuta() {
	  ArrayList<Argitalpen>  kopia = new ArrayList<>(argitalpenak.values());
	    quickSort(kopia, 0, kopia.size() - 1);
	    return kopia;
	}
	private void quickSort(ArrayList<Argitalpen> taulaBat, int hasiera, int bukaera) {
		if (bukaera- hasiera>0) {
	        int indizeaZatiketa= zatiketa(taulaBat, hasiera, bukaera);
	        quickSort(taulaBat, hasiera, indizeaZatiketa - 1);
	        quickSort(taulaBat, indizeaZatiketa + 1, bukaera);
	    }
	}
	private int zatiketa(ArrayList<Argitalpen> taula, int i, int f) {
	    Argitalpen lag = taula.get(i); 
	    int ezker = i;
	    int eskuin = f;

	    while (ezker < eskuin) {
	        while (ezker < eskuin && taula.get(ezker).compareTo(lag) <= 0) {
	            ezker++;
	        }
	        while (taula.get(eskuin).compareTo(lag) > 0) {
	            eskuin--;
	        }
	        if (ezker < eskuin) {
	            swap(taula, ezker, eskuin);
	        }
	    }
	    taula.set(i, taula.get(eskuin));
	    taula.set(eskuin, lag);

	    return eskuin;
	}
	private void swap(ArrayList<Argitalpen> taula, int one, int two) {
	    Argitalpen temp = taula.get(one);
	    taula.set(one, taula.get(two));
	    taula.set(two, temp);
	}
	public void inprimatuGuztiak() {
	    for (Argitalpen a : this.argitalpenak.values()) {
	        System.out.println("Id: " + a.getId() + " - " + a.getIzenburua());
	    }
	}
	
}
