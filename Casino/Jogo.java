interface Jogo {
    void play();
    void iniciar();
    void rodada();
    void finalizar();
    Player getPlayer();
    boolean verificaSaldo(Player player);
}