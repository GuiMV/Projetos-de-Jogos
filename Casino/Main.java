/**
 * Interface inicial onde escolhemos jogar ou encerrar o programa.
 * Pode facilmente implementar novos jogos
*/

/**
  Código desenvolvido por: José Guilherme Moizinho Viana e Kayke Henrique dos Santos Alves
*/

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Construindo o jogador
    System.out.print("Construindo o Jogador\nDigite seu nome: ");
    String nome = input.nextLine().trim();

    double saldo = -1;
    while(saldo < 0){
      System.out.print("Digite seu saldo bancario: ");
      String entrada = input.next().replace(",", ".").trim();

      try {
        saldo = Double.parseDouble(entrada);

        if(saldo < 0)
          System.out.println("Saldo inválido.");
      } catch (NumberFormatException e) {
        System.out.println("Valor inválido. Digite um número válido.");
      }
    }

    Player player;
    if (nome.isEmpty())
      player = new Player(saldo);
    else
      player = new Player(nome, saldo);

    int opcao = 1;
    while(opcao != 0){
      // Menu Principal
      System.out.print("\033[H\033[2J"); // Limpa tela
      System.out.println("\n===  🎰  Casino Dos Deuses 3000  🎰  ===");

      System.out.printf("Jogador: %s | Saldo: R$ %.2f%n", player.getNome(), player.getSaldo());
      
      System.out.println("\nEscolha um jogo:");
      System.out.println("1 - Blackjack (21)");
      System.out.println("2 - Maior Carta");
      System.out.println("0 - Sair");
      System.out.print(">> ");

      while (!input.hasNextInt()) {
        System.out.print("Entrada inválida. Digite um número: ");
        input.next();
      }
      opcao = input.nextInt();
      
      Jogo jogo; // Jogo Genérico
                                                                
      switch (opcao) {
        case 1:
          jogo = new BJ21(player);                                    // Jogo específico
          jogo.play();                                                // Executa o jogo
          break;

          case 2:
          jogo = new MaiorCarta(player);                                // Jogo específico
          jogo.play();                                                // Executa o jogo
          break;

        case 0:
          System.out.println("\nSaindo do casino... Até a próxima!");
          break;

        default:
          System.out.println("\nOpção inválida!");
      }

      if (opcao == 0) break;

      System.out.printf("\nSaldo atual: R$ %.2f%n", player.getSaldo()); //mostra o saldo do jogador
      // caso o jogador queira depositar mais dinheiro
      System.out.print("Deseja fazer um depósito? (s/n): ");
      if(input.next().trim().equalsIgnoreCase("s")) {
        System.out.print("Deposito: ");
        if(input.hasNextDouble())
          player.receber(input.nextDouble());
      }
      
      System.out.print("\033[H\033[2J");
    }
      
    input.close();
  }
}