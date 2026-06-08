import java.util.Scanner;

public class Jogador{
    /**              Afazeres do jogador
     * 1º Ele tem 6 'vidas', ou seja, 6 chances antes de ser enforcado;
     * 2º 'win' contabiliza os acertos do jogador (variável importante para o loop)
     * 3º 'palpitar' é a função principal do jogador.
     * 4º Por fim, 'acerto' e 'erro' atualizam os atributos do jogador.
     */
    Scanner input = new Scanner(System.in);
    
    public int vidas;
    public int win;
    
    public Jogador(){
        this.vidas = 6;
        this.win = 0;
    }
    
    public char palpitar(){
        /**
         * A leitura se repete até que uma letra seja digitada;
         * Após a validação, é retornado um char maiúsculo
         */
        while(true){
            String l = input.nextLine().toUpperCase();
            if (l.isEmpty() || !Character.isLetter(l.charAt(0)))
                continue; 
            return l.charAt(0);
        }
    }
    
    public void acerto(){
        this.win++;
    }
    public void erro(){
        this.vidas--;
    }
    
}
