package programa;

import java.util.Scanner;

public class Teklatua {
    
    private static Teklatua nireTeklatua = null;
    private Scanner sc;
    
    private Teklatua() {
        sc = new Scanner(System.in);
    }
    
    public int irakurriInt() {
        boolean ezInteger = true;
        int zenbat = 0;
        do {
            try {
                String input = sc.nextLine().trim();
                zenbat = Integer.parseInt(input);
                ezInteger = false;
            } catch (NumberFormatException e) {
            	System.out.println("Hori ez da zenbaki bat, zenbakia sartu mesedez");
            }
        } while (ezInteger);
        return zenbat;
    }

    public String irakurriString()  {
    	  String input = sc.nextLine();
          return input;
    }
    public static Teklatua getTeklatua() {
        if (nireTeklatua == null) {
            nireTeklatua = new Teklatua();
        }
        return nireTeklatua; 
    }
}