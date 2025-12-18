import java.util.Scanner;

public class Player extends Jogador implements Transferencias {
    private double saldo;

    Scanner input = new Scanner(System.in);

    public Player(double saldo){             // sobrecarga de construtor
        this("Jogador", saldo);
    }
    
    public Player(String nome, double saldo) {
        super(nome);
        this.saldo = saldo;
    }

    public void pagar(double valor){
        this.saldo -= valor;
    }

    public void receber(double valor){
        this.saldo += valor;
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