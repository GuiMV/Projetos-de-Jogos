public class Dealer extends Jogador {
    public Dealer() {
        super("Dealer");
    }

    @Override
    public boolean Comprar() {
        return getPontuacao() < 17; // regra clássica
    }

    public void agir(Baralho baralho) {
        System.out.println("\n--- Dealer jogando ---");

        while (Comprar()) {
            Carta nova = baralho.puxar();
            System.out.println("Dealer compra: " + nova);
            receberCarta(nova);
        }

        System.out.println("Dealer final: " + getPontuacao());
    }
}