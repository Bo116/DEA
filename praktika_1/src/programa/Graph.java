package programa;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	
    HashMap<String, Integer> th;
    String[] keys;
    ArrayList<Integer>[] adjList;
	
    public void grafoaSortu() {
        // Post: pelikulen zerrendatik grafoa sortu
        //       Nodoak aktore izenak dira
		
        EgileZerrenda eZ = EgileZerrenda.getEgileZerrenda();
        ArgitalpenZerrenda aZ = ArgitalpenZerrenda.getArgitalpenZerrenda();

        // 1. pausua:  th bete
        th = new HashMap<>();
        int idCounter = 0;
        for (Egile e : eZ.getEgileak().values()) {
            String izena = e.getIzena().trim();
            if (!th.containsKey(izena)) {
                th.put(izena, idCounter++);
            }
        }

        // 2. pausua: keys bete
        keys = new String[th.size()];
        for (String k: th.keySet()) keys[th.get(k)] = k;

        // 3. pausua: adjList bete
        adjList = (ArrayList<Integer>[]) new ArrayList[th.size()];
        for (int i = 0; i < th.size(); i++) {
            adjList[i] = new ArrayList<>();
        }

        for (Argitalpen arg : aZ.getArgitalpenak().values()) {
            ArrayList<String> authorIds = arg.getEgileak();
            if (authorIds.size() > 1) {
                ArrayList<Integer> nodeIds = new ArrayList<>();
                for (String authId : authorIds) {
                    Egile e = eZ.bilatu(authId);
                    if (e != null) {
                        String name = e.getIzena().trim();
                        if (th.containsKey(name)) {
                            nodeIds.add(th.get(name));
                        }
                    }
                }
                for (int i = 0; i < nodeIds.size(); i++) {
                    for (int j = i + 1; j < nodeIds.size(); j++) {
                        int v = nodeIds.get(i);
                        int w = nodeIds.get(j);
                        if (!adjList[v].contains(w)) adjList[v].add(w);
                        if (!adjList[w].contains(v)) adjList[w].add(v);
                    }
                }
            }
        }
    }
	
    public void print(){
       for (int i = 0; i < adjList.length; i++){
        System.out.print("Element: " + i + " " + keys[i] + " --> ");
        for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
        
        System.out.println();
       }
    }
	
    public boolean erlazionatuta(String a1, String a2){
    	
        if (th == null || !th.containsKey(a1) || !th.containsKey(a2)) return false;

        Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
        int pos1 = th.get(a1);
        int pos2 = th.get(a2);
        boolean aurkitua = false;
        boolean[] aztertuak = new boolean[th.size()];

        aztertuGabeak.add(pos1);
        aztertuak[pos1] = true;

        while (!aztertuGabeak.isEmpty()) {
            int unekoa = aztertuGabeak.poll();
            if (unekoa == pos2) {
                aurkitua = true;
                break;
            }
            for (int auzokidea : adjList[unekoa]) {
                if (!aztertuak[auzokidea]) {
                    aztertuak[auzokidea] = true;
                    aztertuGabeak.add(auzokidea);
                }
            }
        }
        return aurkitua;
    }

    public ArrayList<String> erlazionatutaBidea(String a1, String a2){

        if (!th.containsKey(a1) || !th.containsKey(a2)) {
            System.out.println("Egileetako bat ez da existitzen");
            return null;
        }
        if (th == null || !th.containsKey(a1) || !th.containsKey(a2)) return null;
        String n1 = a1.trim(); 
        String n2 = a2.trim();
        if (th == null || !th.containsKey(n1) || !th.containsKey(n2)) {
            return null;
        }
        int s = th.get(n1);
        int t = th.get(n2);

        if (s == t) {
            ArrayList<String> res = new ArrayList<>();
            res.add(a1);
            return res;
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] aztertuak = new boolean[th.size()];
        int[] edgeTo = new int[th.size()];

        q.add(s);
        aztertuak[s] = true;
        boolean aurkitua = false;

        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == t) {
                aurkitua = true;
                break;
            }
            for (int w : adjList[v]) {
                if (!aztertuak[w]) {
                    aztertuak[w] = true;
                    edgeTo[w] = v;
                    q.add(w);
                }
            }
        }

        if (!aurkitua) return null;

        ArrayList<String> path = new ArrayList<>();
        int curr = t;
        while (curr != s) {
            path.add(keys[curr]);
            curr = edgeTo[curr];
        }
        path.add(keys[s]);
        Collections.reverse(path);
        return path;
    }
    public boolean isEmpty() {
        return th == null || th.isEmpty();
    }
    public HashMap<String, Double> randomWalkPageRank() {
        HashMap<String, Double> emaitza = new HashMap<>();
        if (keys == null || keys.length == 0) return emaitza;

        int N = keys.length;
        double[] aldiak = new double[N];
        double pausuak = 0;
        
        int iterazioak = N * 1000;
        Random rand = new Random();

        for (int i = 0; i < iterazioak; i++) {
            int oraingoa = rand.nextInt(N);
            Set<Integer> ibilbideHonetanBisitatua = new HashSet<>();
            aldiak[oraingoa]++;
            ibilbideHonetanBisitatua.add(oraingoa);
            pausuak++;
            boolean gelditu = false;
            while (!gelditu) {
                if (rand.nextDouble() > 0.85) {
                    gelditu = true;
                    break;
                }
                ArrayList<Integer> auzokideak = adjList[oraingoa];
                if (auzokideak == null || auzokideak.isEmpty()) {
                    gelditu = true;
                    break;
                }
                int hurrengoa = auzokideak.get(rand.nextInt(auzokideak.size()));
                if (ibilbideHonetanBisitatua.contains(hurrengoa)) {
                    gelditu = true;
                    break;
                }
                oraingoa = hurrengoa;
                ibilbideHonetanBisitatua.add(oraingoa);
                aldiak[oraingoa]++;
                pausuak++;
            }
        }
        for (int i = 0; i < N; i++) {
            if (pausuak > 0) {
                emaitza.put(keys[i], aldiak[i] / pausuak);
            } else {
                emaitza.put(keys[i], 0.0);
            }
        }

        return emaitza;
    }
    public HashMap<String, Double> pageRank() {
        HashMap<String, Double> emaitza = new HashMap<>();
        if (keys == null || keys.length == 0) return emaitza;

        int N = keys.length;
        double[] oraingoPR = new double[N];
        double dampingFactor = 0.85;
        double limitea = 0.0001;
        if (N < 20) System.out.print("Iter 0: ");
        for (int i = 0; i < N; i++) {
            oraingoPR[i] = 1.0 / N;
            if (N < 20) System.out.printf("%s=%.4f  ", keys[i], oraingoPR[i]);
        }
        if (N < 20) System.out.println();

        boolean converged = false;
        int iteracion = 1;
        
        while (!converged) {
            double[] nextPR = new double[N];
            double baseValue = (1.0 - dampingFactor) / N;
            for (int i = 0; i < N; i++) nextPR[i] = baseValue;
            for (int i = 0; i < N; i++) {
                int outDegree = adjList[i].size();
                if (outDegree > 0) {
                    double share = (oraingoPR[i] * dampingFactor) / outDegree;
                    for (int neighbor : adjList[i]) {
                        nextPR[neighbor] += share;
                    }
                }
            }
            double diff = 0.0;
            for (int i = 0; i < N; i++) {
                diff += Math.abs(nextPR[i] - oraingoPR[i]);
            }
            
            oraingoPR = nextPR;
            if (N < 20) {
                System.out.print("Iter " + iteracion + ": ");
                for (int i = 0; i < N; i++) {
                    System.out.printf("%s=%.4f  ", keys[i], oraingoPR[i]);
                }
                System.out.println();
            }
            if (diff < limitea) {
                converged = true;
            }
            iteracion++;
        }

        for (int i = 0; i < N; i++) {
            emaitza.put(keys[i], oraingoPR[i]);
        }
        return emaitza;
    }
    public void probaGrafoTxikiarekin() {
        th = new HashMap<>();
        th.put("A", 0);
        th.put("B", 1);
        th.put("C", 2);
        th.put("D", 3);

        keys = new String[]{"A", "B", "C", "D"};

        adjList = (ArrayList<Integer>[]) new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            adjList[i] = new ArrayList<>();
        }
        adjList[1].add(0);
        adjList[1].add(2);
        adjList[2].add(0);
        adjList[3].add(0); 
        adjList[3].add(1); 
        adjList[3].add(2); 
        System.out.println("Proba grafoa (A, B, C, D) zuzen kargatu da.");
    }
}