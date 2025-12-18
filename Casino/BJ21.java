/**
 * Jogo Black Jack (21)
 * Como todo jogo, ele implementa a interface Jogo
 * Aqui, o jogador joga contra um Dealer, buscando chegar o mais próximo de 21 pontos 
*/
import java.util.Scanner;

public class BJ21 implements Jogo {
    private final Baralho baralho = new Baralho();
    private final Player player = new Player("Você");
    private final Dealer dealer = new Dealer();
    
    public void play() {
        if(player.getSaldo() >= 200){
            iniciar();                  // Compra as 2 primeiras cartas
            rodada();                   // Player e Dealer compram até parar
            finalizar();                // Verifica quem Ganhou e volta pra Main
        } else {
            System.out.println("Saldo insuficiente");
        }
    }
    
    public void iniciar() {
        baralho.embaralhar();
        player.limparMao();
        dealer.limparMao();
        
        for (int i = 0; i < 2; i++) {
            player.receberCarta(baralho.puxar());
            dealer.receberCarta(baralho.puxar());
        }   // Cada um compra 2 cartas de início
        
        System.out.println("\n--- Suas cartas: " + player.getMao());
        System.out.println("Pontuação: " + player.getPontuacao());
        System.out.println("\n--- Carta visível do Dealer: " 
                            + dealer.getMao().get(0));
    }
    
    public void rodada() {
        Scanner input = new Scanner(System.in);
        
        // Turno do jogador
        while (player.getPontuacao() < 21 && player.Comprar()) {
            Carta nova = baralho.puxar();
            System.out.println("Você comprou: " + nova);
            player.receberCarta(nova);
            System.out.println("Pontuação: " + player.getPontuacao());
        }
        
        // Turno do dealer
        dealer.agir(baralho);
    }
    
    public void finalizar() {
        int p = player.getPontuacao();
        int d = dealer.getPontuacao();

        System.out.println("\n=== FINAL ===");
        System.out.println("Suas cartas: " + player.getMao() + " (" + p + ")");
        System.out.println("Dealer: " + dealer.getMao() + " (" + d + ")\n");

        if (p > 21)       System.out.println("Você estourou! Dealer vence.");
        else if (d > 21)  System.out.println("Dealer estourou! Você vence!");
        else if (p > d)   System.out.println("Você venceu!");
        else if (p < d)   System.out.println("Dealer venceu!");
        else              System.out.println("Empate!");
    }
    
    public Player getPlayer(){
        return this.player;
    }
}