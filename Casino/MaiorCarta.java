import java.util.Scanner;

public class MaiorCarta implements Jogo{
  private Player jogador;
  private Baralho baralho;
  private Scanner input = new Scanner(System.in);

  public MaiorCarta(Player jogador){
    this.jogador = jogador;
    this.baralho = new Baralho();
  }

  public Player getPlayer() {
      return this.jogador;
  }

  @Override
  public void play() {
    if(verificaSaldo(jogador)) {
        System.out.println("Saldo insuficiente para jogar. Você precisa de pelo menos R$100.");
        return;
    }
    
    iniciar();
    rodada();
    finalizar();
  }
    
  public boolean verificaSaldo(Player jogador){
      return jogador.getSaldo() < 100;
  }

  @Override
  public void iniciar() {
      System.out.println("\n=== MAIOR CARTA ===");
      System.out.println("Jogador: " + jogador.getNome());
      System.out.printf("Saldo atual: R$ %.2f\n", jogador.getSaldo());

      baralho = new Baralho();
      baralho.embaralhar();

      System.out.println("Pressione ENTER para começar...");
      input.nextLine();
  }

  @Override
  public void rodada() {
      System.out.println("\n--- RODADA ---");

      Carta Mao = baralho.puxar();
      Carta CPU = baralho.puxar();

      System.out.println("Sua carta: " + Mao);
      System.out.println("Carta da máquina: " + CPU);

    int[] valores = new int[2];
    int i = 0;
      for (Carta c : new Carta[]{Mao, CPU}){
        switch (c.getValor()){
            case "A":
              valores[i] = 11;
              break;
            case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "10":
              valores[i] = Integer.parseInt(c.getValor());
              break;
            case "J": case "Q": case "K":
              valores[i] =  1;
              break;
        }
        i++;
      } 

      if (valores[0] > valores[1]) {
          System.out.println("Você venceu! +R$ 100");
          jogador.receber(100);
      } else if (valores[0] < valores[1]) {
          System.out.println("Você perdeu! -R$ 100");
          jogador.pagar(100);
      } else {
          System.out.println("Empate!");
      }
  }

  @Override
  public void finalizar() {
      System.out.printf("\nSaldo final: R$ %.2f\n", jogador.getSaldo());
      System.out.println("=== FIM DO JOGO ===\n");
  }
}