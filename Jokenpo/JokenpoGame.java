import java.util.Scanner;

public class JokenpoGame{
    // Atributos
    private Player[] Players;
    private final boolean jogadorHumano;
    private final boolean melhorTres;

    // Construtor
    public JokenpoGame(Player[] lPlayers, boolean JH, boolean MT){
        this.Players = lPlayers;
        this.jogadorHumano = JH;
        this.melhorTres = MT;
    }
    
    // Função principal: Inicia o jogo
    public void start(){
        System.out.print("\n=== Jokenpo ===\nAlvo: ");
        System.out.printf("%s\n", (this.melhorTres ? "Melhor de 3\n" : "3 vitórias\n"));
        
        int Turn = (this.jogadorHumano ? 
                        (this.melhorTres ? m3(true) : v3(true)) :
                        (this.melhorTres ? m3(false) : v3(false)));

        System.out.println("=== Fim de Jogo ===\nRodadas Totais: "+ Turn);
        System.out.println("Placar Final: "+ placar().stripTrailing());
        int Max = Math.max(Players[0].getWin(),
                  Math.max(Players[1].getWin(),
                           Players[2].getWin()));
        System.out.printf("Vencedores: %s%s%s", (Players[0].getWin() == Max ? Players[0].getName()+" " : ""),
                                                 (Players[1].getWin() == Max ? Players[1].getName()+" " : ""),
                                                 (Players[2].getWin() == Max ? Players[2].getName()+" " : ""));
    }
    
    // Melhor de 3 | Humano ou Máquina
    private int m3(boolean JH){
        int turn = 1;
        
        while (turn <= 3) {
            int win = (JH ? dinamic() : fast());
                
            if (win == -1)
                System.out.println("Resultado Final: Empate!\n");
            else {
                Players[win].incrementWin();
                System.out.println("Vencedor: " + Players[win].getName());
                System.out.println("Placar: "+ placar()+ "\n");
            }
            turn++;
        }
        return --turn;
    }
    // 3 Vitórias | Humano ou Máquina
    private int v3(boolean JH){
        int turn = 1;
        
        while (Players[0].getWin() != 3 && Players[1].getWin() != 3 && Players[2].getWin() != 3) {
            int win = (JH ? dinamic() : fast());
                
            if (win == -1)
                System.out.println("Resultado Final: Empate!\n");
            else {
                Players[win].incrementWin();
                System.out.println("Vencedor: " + Players[win].getName());
                System.out.println("Placar: "+ placar()+ "\n");
            }
            turn++;
        }
        return --turn;
    }

    // Máquina
    private int fast(){
        for (int i = 0; i < 3; i++){
            Players[i].jogada();
            System.out.println(Players[i].getName() +": "+ Players[i].getObj());
        }    // Jogadas Aleatórias
        return winner(Players[0].getObj(),
                      Players[1].getObj(),
                      Players[2].getObj());  // Quem ganha
    }
    // Humano
    private int dinamic(){
        Scanner input = new Scanner(System.in);
        
        System.out.print(Players[2].getName() +": ");
        do {
            Players[2].setObj(input.nextLine());
        } while (!Players[2].getObj().equals("Pedra") &&
                 !Players[2].getObj().equals("Papel") &&
                 !Players[2].getObj().equals("Tesoura"));
        
        for (int i = 0; i < 2; i++){
            Players[i].jogada();
            System.out.println(Players[i].getName() +": "+ Players[i].getObj());
        }
        return winner(Players[0].getObj(),
                      Players[1].getObj(),
                      Players[2].getObj());
    }
    
    // Verificar quem ganha
    private int winner(String J1, String J2, String J3){
            if (J1.equals(J2) && J2.equals(J3)) return -1;
            
            if (x1(J1, J2) && x1(J1, J3)) return 0;
            if (x1(J2, J1) && x1(J2, J3)) return 1;
            if (x1(J3, J1) && x1(J3, J2)) return 2;
            
            return -1;
        }
    private boolean x1(String a, String b){
        return (a.equals("Pedra") && b.equals("Tesoura")) ||
               (a.equals("Tesoura") && b.equals("Papel")) ||
               (a.equals("Papel") && b.equals("Pedra"));
    }  
    
    // Constroi o placar
    private String placar(){
        return String.format("%s = %d | %s = %d | %s = %d",
            Players[0].getName(), Players[0].getWin(),
            Players[1].getName(), Players[1].getWin(),
            Players[2].getName(), Players[2].getWin()
        );
    }
}