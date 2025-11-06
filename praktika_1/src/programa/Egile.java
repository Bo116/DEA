package programa;

import java.util.ArrayList;
import java.util.Iterator;

public class Egile {

    // Atributuak
    private String id;
    private String izena;
    private UnorderedDoubleLinkedList<String> argitalpenak;

    // Eraikitzailea
    public Egile(String id, String izena) {
        this.id = id;
        this.izena = izena;
        this.argitalpenak = new UnorderedDoubleLinkedList<>();
    }

    // Get metodoak
    public String getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    // Argitalpenak kudeatzeko metodoak
    public void gehituArgitalpen(String argId) {
        argitalpenak.addToRear(argId);  // gehitu amaieran
    }

    public boolean argitalpenaDauka(String argId) {
        return argitalpenak.contains(argId);
    }

    public void argitalpenaKendu(String argId) {
        argitalpenak.remove(argId);
    }

    // Egilearen argitalpen guztiak inprimatzeko
    public void printArgitalpenak() {
        System.out.println("Egilearen argitalpenak (" + izena + "):");
        argitalpenak.adabegiakInprimatu();
    }
    public ArrayList<String> getArgitalpenakLista() {
        ArrayList<String> lista = new ArrayList<>();
        Iterator<String> it = argitalpenak.iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        return lista;}

    // Argitalpenen zerrenda lortzeko (fitxategietan gordetzeko adibidez)
    public UnorderedDoubleLinkedList<String> getArgitalpenak() {
        return argitalpenak;
    }
    
}
