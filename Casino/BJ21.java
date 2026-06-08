/**
 * Jogo Black Jack (21)
 * Como todo jogo, ele implementa a interface Jogo
 * Aqui, o jogador joga contra um Dealer, buscando chegar o mais próximo de 21 pontos 
*/

public class BJ21 implements Jogo {
    private final Baralho baralho = new Baralho();
    private final Dealer dealer = new Dealer();
    private Player player;

    public BJ21(Player player){
        this.player = player;
    }
    
    public void play() {
        if(verificaSaldo(player)) { 
            System.out.println("Saldo insuficiente para jogar. Você precisa de pelo menos R$200."); 
            return;
        }
        iniciar();                  // Compra as 2 primeiras cartas
        rodada();                   // Player e Dealer compram até parar
        finalizar();                // Verifica quem Ganhou e volta pra Main
    }

    public boolean verificaSaldo(Player player){
        return player.getSaldo() < 200;
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

        if (p > 21){
            System.out.println("Você estourou! Dealer vence.");
            player.pagar(200);
        }

        else if (d > 21){
            System.out.printf("Dealer estourou! %s vence!\n", player.nome);
            player.receber(200);
        }

        else if (p > d){
            System.out.printf("%s venceu!\n", player.nome);
            player.receber(200);
        }
        else if (p < d){
            System.out.println("Dealer venceu!");
            player.pagar(200);
        }
        else System.out.println("Empate!");
    }

    public Player getPlayer(){
        return this.player;
    }
}