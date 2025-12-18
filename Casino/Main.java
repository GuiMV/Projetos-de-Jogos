/**
 * Interface inicial onde escolhemos jogar ou encerrar o programa.
 * Pode facilmente implementar novos jogos
*/

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    int opcao;
	    
	    System.out.println("\n===  🎰  Casino Dos Deuses 3000  🎰  ===");
	    do {
            System.out.println("\nEscolha um jogo:");
            System.out.println("1 - Blackjack (21)");
            System.out.println("0 - Sair");
            System.out.print(">> ");

            while (!input.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                input.next();
            }
            
            opcao = input.nextInt();
	        Jogo jogo;                                                          // Jogo Genérico
	    
    	    switch (opcao) {
                case 1:
                    jogo = new BJ21();                                          // Jogo específico
                    jogo.play();                                                // Executa o jogo
                    
                    System.out.print("Deseja continuar? (s/n): ");
                    while(input.next().trim().equalsIgnoreCase("s") && jogo.getPlayer().getSaldo() >= 200){
                        jogo.play();
                        System.out.print("Deseja continuar? (s/n): ");
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do casino... Até a próxima!");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }
	    } while (opcao != 0);
	    
	    input.close();
	}
}