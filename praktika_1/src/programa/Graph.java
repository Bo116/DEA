package programa;

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
        if (th == null || !th.containsKey(a1) || !th.containsKey(a2)) return null;

        int s = th.get(a1);
        int t = th.get(a2);

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
}