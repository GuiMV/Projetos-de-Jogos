import java.util.Scanner;

public class GameLauncher{
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.println("(S, N) Jogador Humano?");
        char player = input.nextLine().toUpperCase().charAt(0);
        System.out.println("(S, N) Melhor de 3?");
        boolean tres = input.nextLine().toUpperCase().charAt(0) == 'S';
        
        Player[] listPlayers = new Player[3];
        JokenpoGame Game;
        switch (player){
            case 'S':
                System.out.print("\nNome: ");
                // input.nextLine(); // limpar o buffer
                String name = input.nextLine();

                listPlayers = new Player[]{
                    new Player("Zoro"),
                    new Player("Yugi"),
                    new Player(name)
                };
                Game = new JokenpoGame(listPlayers, true, tres);
                
                Game.start();
                break;
            case 'N':
                System.out.println("\nJogadores: Jarvis, BMO, BB-8");
                
                listPlayers = new Player[]{
                    new Player("Jarvis"),
                    new Player("BMO"),
                    new Player("BB-8")
                };
                Game = new JokenpoGame(listPlayers, false, tres);
                
                Game.start();
                break;
            default:
                break;
        }
    }
}