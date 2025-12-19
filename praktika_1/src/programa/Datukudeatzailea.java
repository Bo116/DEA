package programa;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
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
		Graph grafoa=null;
		boolean jarraitu = true;
		boolean datuakKargatuta = false;
		Set<Integer> datuakBeharrezkoak = Set.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ,13,14,15,16,17);
		
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
		System.out.println("13. 2 praktikako probak");
		System.out.println("14. Bilatu egileen arteko erlazioa");
		System.out.println("15.Kalkulatu daitezkeen erlazio kopurua");
		System.out.println("16. 4.Praktika proba azkarra");
		System.out.println("17.4.Praktikako bi metodoak");
		System.out.println("18.Irten");
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
				System.out.println("Aipamena gehitu da argitalpenari: " + aipamenId + " - " + argitalpenId);
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
					System.out.println("Egilea gehitu da argitalpenari: " + egileId + " - " + argitalpenId2);
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
			System.out.println("\n--- PROBA: Egile baten zerrenda berria ---");

			String egileIdProba = "Q46250423";  // Donatella Taramelli
			Egile egileProba = EgileZerrenda.getEgileZerrenda().bilatu(egileIdProba);

			if (egileProba != null) {
			    System.out.println("Egilea aurkitu da: " + egileProba.getId() + " - " + egileProba.getIzena());

			    System.out.println("\nEgilearen hasierako argitalpenak:");
			    egileProba.printArgitalpenak();

			    // Proba: elementu bat bilatu
			    String bilatuaId = "Q34687796";
			    System.out.println("\nArgitalpena '" + bilatuaId + "' zerrendan dago? " +
			        egileProba.argitalpenaDauka(bilatuaId));

			    // Proba: elementu bat gehitu eta berriz inprimatu
			    String berria = "Q99999999";
			    System.out.println("\nGehitzen: " + berria);
			    egileProba.gehituArgitalpen(berria);
			    System.out.println("Eguneratutako zerrenda:");
			    egileProba.printArgitalpenak();

			    // Proba: elementu bat ezabatu
			    System.out.println("\nKenduko da: " + bilatuaId);
			    egileProba.argitalpenaKendu(bilatuaId);
			    System.out.println("Zerrenda ondoren:");
			    egileProba.printArgitalpenak();

			    // Proba: hutsik dagoen ala ez
			    System.out.println("\nZerrenda hutsa? " + egileProba.getArgitalpenak().isEmpty());
			} else {
			    System.out.println("⚠️ Ez da aurkitu egilea id honekin: " + egileIdProba);
			}
			break;
		case 14: // Bilatu erlazioa
	
		    if (grafoa == null || grafoa.isEmpty()) {
		        System.out.println("Grafoa sortzen...");
		        grafoa = new Graph();
		        grafoa.grafoaSortu();
		    }
		    if (grafoa.isEmpty()) {
		        System.out.println("⚠️ Grafoa hutsik dago. Datuak kargatu dituzu (1. aukera)?");
		        break;
		    }
		
		    System.out.println("Sartu lehenengo egilearen izena:");
		    String izenaA = Teklatua.getTeklatua().irakurriString();
		    
		    System.out.println("Sartu bigarren egilearen izena:");
		    String izenaB = Teklatua.getTeklatua().irakurriString();
		    
            // --- HASIERA CRONOMETROA ---
		    long hasiera = System.currentTimeMillis(); 
            
		    ArrayList<String> bidea = grafoa.erlazionatutaBidea(izenaA, izenaB);
            
            // --- AMAIERA CRONOMETROA ---
		    long amaiera = System.currentTimeMillis();
            
            // Inprimatu denbora
            System.out.println("⏳ Bilaketa denbora: " + (amaiera - hasiera) + " ms");
		    
		    if (bidea != null) {
		        System.out.println("\n✅ Bidea: " + String.join(" -> ", bidea));
		    } else {
		        System.out.println("\n❌ Ez da erlaziorik aurkitu.");
		    }
		    break;
		case 15: 
	
		    if (grafoa == null || grafoa.isEmpty()) {
		        System.out.println("Grafoa sortzen...");
		        grafoa = new Graph();
		        grafoa.grafoaSortu();
		    }
		    if (grafoa.isEmpty()) {
		        System.out.println("⚠️ Grafoa hutsik dago. Datuak kargatu dituzu (1. aukera)?");
		        break;
		    }

	
		    System.out.println("Sartu lehenengo egilearen izena:");
		    String izenaStart = Teklatua.getTeklatua().irakurriString();
		    
		    System.out.println("Sartu bigarren egilearen izena:");
		    String izenaEnd = Teklatua.getTeklatua().irakurriString();

		    System.out.println("Errendimendua kalkulatzen (itxaron mesedez)...");
		    
		
		     hasiera = System.currentTimeMillis();
		    int errepikapenak = 1000; 

		    for (int i = 0; i < errepikapenak; i++) {
		        grafoa.erlazionatutaBidea(izenaStart, izenaEnd);
		    }

		     amaiera = System.currentTimeMillis();
		    long denboraTotala = amaiera - hasiera; 


		    System.out.println("\n--- ERRENDIMENDU DATUAK ---");
		    System.out.println(errepikapenak + " bilaketa egiteko denbora: " + denboraTotala + " ms");

		    if (denboraTotala > 0) {
		        double bilaketaMinutuko = (errepikapenak * 60000.0) / denboraTotala;
		        System.out.println("ESTIMAZIOA: " + (int)bilaketaMinutuko + " bilaketa/minutuko");
		    } else {
		        System.out.println("Oso azkarra izan da (0 ms).");
		    }
		    break;
				
		
		case 16:
			System.out.println("--- PROBA PAGERANK (Adibidea PDF: A, B, C, D) ---");
			grafoa = new Graph();
			
			grafoa.probaGrafoTxikiarekin(); 
			
			System.out.println("\nPageRankExekutatzen...");
			java.util.HashMap<String, Double> emaitzaPR = grafoa.pageRank();
			
			System.out.println("\nPageRankEmaitzak:");
			emaitzaPR.entrySet().forEach(entry -> {
			    System.out.println(entry.getKey() + ": " + String.format("%.4f", entry.getValue()));
			});
			
			System.out.println("\nRandonWalk exekutatzen...");
			java.util.HashMap<String, Double> emaitzaRW = grafoa.randomWalkPageRank();
			emaitzaRW.entrySet().forEach(entry -> {
			    System.out.println(entry.getKey() + ": " + String.format("%.4f", entry.getValue()));
			});
			break;
		case 17: 
            if (!datuakKargatuta) {
                System.out.println("Lehenengo datuak kargatu behar dira");
                break;
            }

            boolean grafoTxikiaDa = (grafoa != null && grafoa.keys != null && grafoa.keys.length < 100);

            if (grafoa == null || grafoa.isEmpty() || grafoTxikiaDa) {
                System.out.println("Grafo handia sortzen datu errealekin...");
                grafoa = new Graph();
                grafoa.grafoaSortu();
                System.out.println("Grafo handia sortua.");
            }


            System.out.println("\n--- 1. PAGERANK Kalkulatzen ---");
            long startPR = System.currentTimeMillis();
            
            HashMap<String, Double> resPR = grafoa.pageRank();
            
            long endPR = System.currentTimeMillis();
            System.out.println("Bukatua " + (endPR - startPR) + " ms-etan.");
            System.out.println("10 emaitza bisitatueank PAGERANK:");
            inprimatuLehenengo10(resPR);

 
            System.out.println("\n--- 2. RANDOM WALK kalkulatzen---");
            long startRW = System.currentTimeMillis();
            
            HashMap<String, Double> resRW = grafoa.randomWalkPageRank();
            
            long endRW = System.currentTimeMillis();
            System.out.println("Bukatua  " + (endRW - startRW) + " ms-tan");
            System.out.println("10 emaitza bisitatueank:");
            inprimatuLehenengo10(resRW);
            
            break;
		case 18:
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
    private static void inprimatuLehenengo10(HashMap<String, Double> map) {
        if (map == null || map.isEmpty()) {
            System.out.println("   (Ez dago emaitzarik)");
            return;
        }
        ArrayList<java.util.Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        int count = 0;
        for (java.util.Map.Entry<String, Double> entry : list) {
            System.out.printf("   %2d. %-30s  (Valor: %.6f)\n", (count + 1), entry.getKey(), entry.getValue());
            count++;
            if (count >= 10) break;
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
	        	String argList = String.join(",", e.getArgitalpenakLista());
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
	

