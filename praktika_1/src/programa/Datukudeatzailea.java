package programa;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*PROGRAMAK EGIN BEHAR DITUEN FUNTZIOAK:
• Datuak kargatu fitxategietatik
• Argitalpen baten bilaketa, bere identifikatzailea emanda
• Argitalpen berri baten txertaketa (bere identifikatzailea eta izenburua emanda)
• Aipamen bat gehitu argitalpen bati (argitalpenen bi identifikatzaile emanda)
• Egile bat gehitu argitalpen bati (bi identifikatzaile emanda)
• Argitalpen bat emanda (identifikatzailea), bueltatu aipatzen dituen argitalpenak
• Argitalpen bat emanda (identifikatzailea), bueltatu egileak
• Egile bat emanda (identifikatzailea), bueltatu bere argitalpenak
• Ezabatu argitalpen bat
• Ezabatu egile bat
• Argitalpenen eta egileen zerrendak (eguneratua) fitxategietan gorde
• Argitalpenen zerrenda alfabetikoki ordenatua lortu (ez da argitalpenen zerrenda aldatu behar,
ArrayList edo LinkedList motako zerrenda bat bueltatuko da). Ordenaziorako algoritmo bat
inplementatu behar da, hau da, ezin da deitu dagoeneko inplementatuta dagoen ordenaziorako
funtzio bat.*/
import java.util.Set;

public class Datukudeatzailea {
	
	private static Datukudeatzailea nDatuKudeatzailea = null;
	
	public static Datukudeatzailea getDatuKudeatzailea() {
		if (nDatuKudeatzailea == null) {
			nDatuKudeatzailea = new Datukudeatzailea();
		}
		return nDatuKudeatzailea;
	}
	private Datukudeatzailea() {
		
	}
	
	public static void main(String[] args) {
		Datukudeatzailea dK = Datukudeatzailea.getDatuKudeatzailea();
		ArgitalpenZerrenda aZ = ArgitalpenZerrenda.getArgitalpenZerrenda();
		EgileZerrenda eZ = EgileZerrenda.getEgileZerrenda();
		boolean jarraitu = true;
		boolean datuakKargatuta = false;
		Set<Integer> datuakBeharrezkoak = Set.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		
		while(jarraitu) {
		System.out.println("Aukeratu funtzio bat:");
		System.out.println("1.Datuak fitxategietatik kargatu");
		System.out.println("2.Bilatu argitalpen bat identifikatzailea emanda");
		System.out.println("3.Gehitu argitalpen berri bat");
		System.out.println("4.Gehitu aipamen bat argitalpen bati");
		System.out.println("5.Gehitu egile bat argitalpen bati");
		System.out.println("6.Argitalpen bat emanda, bueltatu aipatzen dituen argitalpenak");
		System.out.println("7.Argitalpen bat emanda, bueltatu egileak");
		System.out.println("8.Egile bat emanda, bueltatu bere argitalpenak");
		System.out.println("9.Ezabatu argitalpen bat");
		System.out.println("10.Ezabatu egile bat");
		System.out.println("11.Argitalpenen zerrenda alfabetikoki ordenatua lortu");
		System.out.println("12.Argitalpenen eta egileen zerrendak (eguneratua) fitxategietan gorde");
		System.out.println("13.Irten");
		int aukera = Teklatua.getTeklatua().irakurriInt();
		if (datuakBeharrezkoak.contains(aukera) && !datuakKargatuta) {
			System.out.println("⚠️ Lehenik datuak kargatu behar dituzu (aukera 1).");
			System.out.println("\nSakatu ENTER jarraitzeko...");
			Teklatua.getTeklatua().irakurriString();
			continue;
		}
		switch (aukera) {
		case 1:
			System.out.println("Datuak kargatzen...");
			dK.readFile("src/Datuak/authors-name-all.txt");
			dK.readFile("src/Datuak/publications-titles-all.txt");
			dK.readFile("src/Datuak/publications-citedPubs-all.txt");
			dK.readFile("src/Datuak/publications-authors-all-final.txt");
			System.out.println("Datuak fitxategietatik kargatu dira.");
			datuakKargatuta = true;
			break;
		case 2:
				System.out.println("Sartu bilatu nahi duzun argitalpenaren identifikatzailea:");
				String bilatuId = Teklatua.getTeklatua().irakurriString();
				Argitalpen a = aZ.bilatu(bilatuId);
				if (a != null) {
					System.out.println("Argitalpena aurkitu da: " + a.getId() + " - " + a.getIzenburua());
				}
				else {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + bilatuId);
				}
				break;
		case 3:
				System.out.println("Sartu gehitu nahi duzun argitalpenaren identifikatzailea:");
				String gehituId = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(gehituId) != null) {
					System.out.println("⚠️ Argitalpen hori jadanik existitzen da.");
					break;
				}
				System.out.println("Sartu gehitu nahi duzun argitalpenaren izenburua:");
				String gehituIzenburua = Teklatua.getTeklatua().irakurriString();
				aZ.addArgitalpen(gehituId, gehituIzenburua);
				System.out.println("Argitalpena gehitu da: " + gehituId + " - " + gehituIzenburua);
				break;
		case 4:
			    System.out.println("Sartu aipamen bat gehitu nahi diozun argitalpenaren identifikatzailea:");
				String argitalpenId = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(argitalpenId) == null) {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + argitalpenId);
					break;
				}
				System.out.println("Sartu aipamenaren identifikatzailea:");
				String aipamenId = Teklatua.getTeklatua().irakurriString();
				if(aZ.bilatu(argitalpenId).aipamenaDauka(aipamenId)) {
					System.out.println("⚠️ Aipamen hori jadanik existitzen da argitalpen horretan.");
					break;
				}
				dK.gehituAipamen(argitalpenId, aipamenId);
				break;
		case 5:
				System.out.println("Sartu egile bat gehitu nahi diozun argitalpenaren identifikatzailea:");
				String argitalpenId2 = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(argitalpenId2) == null) {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + argitalpenId2);
					break;
				}
				System.out.println("Sartu egilearen identifikatzailea:");
				String egileId = Teklatua.getTeklatua().irakurriString();
				if(eZ.bilatu(egileId) == null) {
					System.out.println("⚠️ Ez da aurkitu egilerik id honekin: " + egileId);
					break;
				}
				if(aZ.bilatu(argitalpenId2).egileaDauka(egileId)) {
					System.out.println("⚠️ Egile hori jadanik existitzen da argitalpen horretan.");
					break;
				}
				dK.gehituEgilea(argitalpenId2, egileId);
				if(EgileZerrenda.getEgileZerrenda().bilatu(egileId) == null) {
					System.out.println("⚠️ Ez da aurkitu egilerik id honekin: " + egileId);
					break;
				}
				if(EgileZerrenda.getEgileZerrenda().bilatu(egileId) != null) {
					EgileZerrenda.getEgileZerrenda().bilatu(egileId).gehituArgitalpen(argitalpenId2);
				}
				break;
		case 6:
				System.out.println("Sartu argitalpenaren ID-a");
				String argitalpenId3 = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(argitalpenId3) == null) {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + argitalpenId3);
					break;
				}
				dK.aipamenakInprimatu(argitalpenId3);
				break;
		case 7:
				System.out.println("Sartu argitalpenaren ID-a");
				String argitalpenId4 = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(argitalpenId4) == null) {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + argitalpenId4);
					break;
				}
				dK.egileakInprimatu(argitalpenId4);
				break;
		case 8:
				System.out.println("Sartu egilearen ID-a");
				String egileId2 = Teklatua.getTeklatua().irakurriString();
				Egile e = EgileZerrenda.getEgileZerrenda().bilatu(egileId2);
				if (e != null) {
					e.printArgitalpenak();
				}
				else {
					System.out.println("⚠️ Ez da aurkitu egilerik id honekin: " + egileId2);
				}
				break;
		case 9:
				System.out.println("Sartu ezabatu nahi duzun argitalpenaren identifikatzailea:");
				String ezabatuId = Teklatua.getTeklatua().irakurriString();
				if (aZ.bilatu(ezabatuId) == null) {
					System.out.println("⚠️ Ez da aurkitu argitalpenik id honekin: " + ezabatuId);
					break;
				}
				dK.kenduArgitalpen(ezabatuId);
				System.out.println("Argitalpena ezabatu da: " + ezabatuId);
				break;
		case 10:
				System.out.println("Sartu ezabatu nahi duzun egilearen identifikatzailea:");
				String ezabatuEgileId = Teklatua.getTeklatua().irakurriString();
				if (eZ.bilatu(ezabatuEgileId) == null) {
					System.out.println("⚠️ Ez da aurkitu egilerik id honekin: " + ezabatuEgileId);
					break;
				}
				dK.ezabatuEgilea(ezabatuEgileId);
				System.out.println("Egilea ezabatu da: " + ezabatuEgileId);
				break;
		case 11:
				ArrayList<Argitalpen> ordenatuta = dK.getArgitalpenakOrdenatuta();
				System.out.println("Argitalpenen zerrenda alfabetikoki ordenatua:");
				for (int i = 0; i < ordenatuta.size(); i++) {
					Argitalpen arg = ordenatuta.get(i);
					System.out.println(arg.getId() + " - " + arg.getIzenburua());
				}
				break;
		case 12:
				dK.gordeFitxategietan();
				System.out.println("Argitalpenen eta egileen zerrendak (eguneratua) fitxategietan gorde dira.");
				break;
		case 13:
				System.out.println("Irten da programa.");
				jarraitu = false;
				break;
		default:
			System.out.println("Aukera okerra");
			break;
				
		}
		System.out.println("\nSakatu ENTER jarraitzeko...");
		Teklatua.getTeklatua().irakurriString();	
		}
		
	}
	public void readFile(String izena) {
		  try {
		    Scanner sarrera = new Scanner(new FileReader(izena));

		    String lerroa;
		    int kont = 0;
		    while (sarrera.hasNext()) {
		    	
		      lerroa = sarrera.nextLine();
		      kont++; 
		      String[] zatitu = lerroa.split("#");
		      zatitu[0] = zatitu[0].trim();
		      zatitu[1] = zatitu[1].trim();
		     if (izena.contains("authors-name-all")) {
		    	 EgileZerrenda.getEgileZerrenda().addEgile(zatitu[0], zatitu[1]);
		     }
		     else if (izena.contains("publications-titles-all")) {
		    	 ArgitalpenZerrenda.getArgitalpenZerrenda().addArgitalpen(zatitu[0], zatitu[1]);
		     }
		     else if (izena.contains("publications-citedPubs-all")) {
		    	 ArgitalpenZerrenda.getArgitalpenZerrenda().addAipamen(zatitu[0], zatitu[1]);
		    	 
		     }
		     else if (izena.contains("publications-authors-all-final")) {
		    	 ArgitalpenZerrenda.getArgitalpenZerrenda().addEgilea(zatitu[0], zatitu[1]);
		    	 EgileZerrenda.getEgileZerrenda().bilatu(zatitu[1]).gehituArgitalpen(zatitu[0]);
		    	 
		     }
		     else {
		    	 System.out.println("Fitxategi okerra");
		     }
		      
		    } 
		    sarrera.close();
		  }
		  catch (IOException e) {
		    e.printStackTrace();
		  } 
		}
	public void aipamenakInprimatu(String argitalpenId) {
		Argitalpen a =ArgitalpenZerrenda.getArgitalpenZerrenda().bilatu(argitalpenId);
		if (a != null) {
			a.printAipamenak();
		}
		else {
			System.out.println("Ez da aurkitu argitalpenik id honekin: " + argitalpenId);
		}
	}
	
	public ArrayList<Argitalpen> getArgitalpenakOrdenatuta() {
		return ArgitalpenZerrenda.getArgitalpenZerrenda().getArgitalpenakOrdenatuta();
	}
	public void gehituAipamen(String argitalpenId, String aipamenId) {
		Argitalpen a =ArgitalpenZerrenda.getArgitalpenZerrenda().bilatu(argitalpenId);
		if (a != null) {
			a.gehituAipamen(aipamenId);
		}
		else {
			System.out.println("Ez da aurkitu argitalpenik id honekin: " + argitalpenId);
		}
	}
	public void ezabatuEgilea(String egileId) {
		EgileZerrenda.getEgileZerrenda().kenduEgilea(egileId);
		for (Argitalpen a : ArgitalpenZerrenda.getArgitalpenZerrenda().getArgitalpenak().values()) {
		    if (a.egileaDauka(egileId)) {
		        a.egileaKendu(egileId);
		    }
		}

	}
	public void gehituEgilea(String argitalpenId, String egileId) {
		Argitalpen a =ArgitalpenZerrenda.getArgitalpenZerrenda().bilatu(argitalpenId);
		if (a != null) {
			a.gehituEgilea(egileId);
		}
		else {
			System.out.println("Ez da aurkitu argitalpenik id honekin: " + argitalpenId);
		}
	}
	public void kenduArgitalpen(String argitalpenId) {
		ArgitalpenZerrenda.getArgitalpenZerrenda().kenduArgitalpen(argitalpenId);
		 for (Egile e : EgileZerrenda.getEgileZerrenda().getEgileak().values()) {
		        if (e.argitalpenaDauka(argitalpenId)) {
		            e.argitalpenaKendu(argitalpenId);
		        }
		}
	}
	public void egileakInprimatu(String argitalpenId) {
		Argitalpen a =ArgitalpenZerrenda.getArgitalpenZerrenda().bilatu(argitalpenId);
		if (a != null) {
			a.printEgileak();
		}
		else {
			System.out.println("Ez da aurkitu argitalpenik id honekin: " + argitalpenId);
		}
	}
	public void gordeFitxategietan() {
	    try {
	        PrintWriter pwEgile = new PrintWriter("src/Datuak/egileak.txt");
	        for (Egile e : EgileZerrenda.getEgileZerrenda().getEgileak().values()) {
	            String argList = String.join(",", e.getArgitalpenak()); //
	            pwEgile.println(e.getId() + " # " + e.getIzena() + " # " + argList);
	        }
	        pwEgile.close();
	        PrintWriter pwArg = new PrintWriter("src/Datuak/argitalpenak.txt");
	        for (Argitalpen a : ArgitalpenZerrenda.getArgitalpenZerrenda().getArgitalpenak().values()) {
	            String egList = String.join(",", a.getEgileak()); 
	            String aipList = String.join(",", a.getAipamenak());
	            pwArg.println(a.getId() + " # " + a.getIzenburua() + " # " + egList + " # " + aipList);
	        }
	        pwArg.close();

	        System.out.println("Fitxategiak gorde dira.");
	    } catch (IOException e) {
	        System.out.println("Errorea fitxategiak gordetzean: " + e.getMessage());
	    }
	}

	

	
}
	

