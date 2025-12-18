/**
 * Essa classe é responssável por criar o Baralho do jogos
 * É possível embaralhar e puxar cartas do barálho. 
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;       // Vai servir pra embaralhar

public class Baralho {
    private final List<Carta> cartas = new ArrayList<>();
    private final Random rnd = new Random();
    
    public Baralho() {
        String[] naip = {"COPAS", "OUROS", "PAUS", "ESPADAS"};
        String[] valor = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        
        for (String n : naip) 
            for (String v : valor) 
                cartas.add(new Carta(v, n));
    }
    
    public void embaralhar(){
        // Embaralha os elementos de uma lista usando um algoritmo chamado Knuth Shuffle
        Collections.shuffle(cartas, rnd);
    }
    
    public Carta puxar(){
        return cartas.remove(0);
    }
    
    // Em 21 não é tão util, mas em outros jogos essa função pode servir
    // public int restantes(){
    //     return cartas.size();
    // }
}
    