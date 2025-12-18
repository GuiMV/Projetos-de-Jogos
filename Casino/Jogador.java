import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {
    protected String nome;
    protected List<Carta> mao = new ArrayList<>();

    public Jogador(String nome) { this.nome = nome; }
    
    public void receberCarta(Carta c) { mao.add(c); }
    
    public void limparMao() { mao.clear(); }
    
    public List<Carta> getMao() { return mao; }
    
    public int getPontuacao() {
        int soma = 0;
        int ases = 0;

        for (Carta c : mao) {
            String v = c.getValor();
            
            switch (v){
                case "A":
                    soma += 11;
                    ases++;
                    break;
                    
                case "J": case "Q": case "K":
                    soma += 10;
                    break;
                    
                default:
                    soma += Integer.parseInt(v);
                    break;
            }
        }
        
        // Regra do 21: Se a mão estourar, A passa a valer 1
        while (soma > 21 && ases > 0) {
            soma -= 10;
            ases--;
        }
        
        return soma;
    }
    
    public abstract boolean Comprar();
}