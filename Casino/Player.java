import java.util.Scanner;

public class Player extends Jogador {
    private double saldo;
    
    public Player(String nome) {
        super(nome);
        this.saldo = 3000;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    @Override
    public boolean Comprar() {
        Scanner input = new Scanner(System.in);
        System.out.print("Deseja comprar outra carta? (s/n): ");
        return input.next().trim().equalsIgnoreCase("s");
    }
    
    
}