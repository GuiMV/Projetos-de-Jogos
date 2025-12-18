import java.util.Random;

public class JogoDaForca{
    /**                  Variáveis que julgo Importantes
        * Dicionário de Palavras (dict)      | Palavra sorteada (resposta)
        * Tentativa do Jogador   (tantativa) | Letras já uzadas (usadas)
        * Terminal/Tela do jogo (drow [será explicado])
    */
    private String[] dict;
    private Random rng;
    private final String resposta;
    private final int len;
    private char[] tentativa;
    private char[] usadas;
    private int len2;
    private Jogador player;
    private String[] drow;
    
    public JogoDaForca(){
        this.rng = new Random();
        this.dict = new String[] {"JAVA", "C", "PYTHON",
                                  "JAVASCRIPT", "HTML", "CSS",
                                  "FRONTEND","BACKEND", "POO",
                                  "PROGRAMADOR", "ENGENHARIA", "COMPUTAÇÃO",
                                  "ORIENTADOAOBJETO", "ESTRUTURADEDADOS", "SISTEMASDIGITAIS"
        };
        this.resposta = dict[rng.nextInt(15)];  // Sorteia a palavra
        this.len = resposta.length();
        this.len2 = 0;
        
        this.tentativa = new char[this.len];    // Gera a lista da tentativa (['_', '_', ...])
        for(int i = 0; i < this.len; i++)
            tentativa[i] = '_';
            
        this.usadas = new char[26];
        this.player = new Jogador();
        
        /**
         * 'drow' é a interface geral. Optei pela abordagem usada em Estrutura de Dados;
         * Consiste em uma lista de String que será atualizada após cada ação do usuário.
         */
        this.drow = new String[] {"====== FORCA ======\n",
                                  " #====;",
                                  " |    ",
                                  " |   ",
                                  " |   ",
                                  " |",
                                  "========="
                                 };
    }
    
    public void iniciar(){
        /**               Iniciar
         * É a função principal. É aqui onde o jogo começa e termina;
         * Usei o do - while para exibir a tela ao menos uma vez;
         * O laço quebra ao acertar a palavra ou se o jogador for enforcado;
         * Enquanto isso: 1-exibo o terminal, 2-Leio a entrada, 3-Verifico o que aconteceu
        */
        do {
            terminal();
            verificar(player.palpitar());
        } while (this.player.vidas != 0 && this.player.win != this.len);
        
        terminal();                             // Tela final
        if (this.player.vidas == 0)             // Condição de vitória/derrota
            System.out.println("Derrota :(");
        else
            System.out.println("Vitória :)");
    }
    
    private void terminal(){
        System.out.print("\033[H\033[2J");  // Limpo e re-desenho o terminal
        for(int i = 0; i < 7; i++)
            System.out.println(this.drow[i]);
        
        System.out.print("Usadas: ");       // Exibindo a lista de letras usadas
        for(char i : this.usadas)
            System.out.print(i);
        
        System.out.print("\nPalavra: ");    // Exibindo o estado atual da tentativa
        for(char i : this.tentativa)
            System.out.print(i+" ");
        
        System.out.print("\n>> ");          // Finalizando, pronto para receber a entrada
    }
    
    private void verificar(char p){
        /**                        Verificações Necessárias
         * 1ºif - Evita que o programa contabilize letras já usadas
         * 2ºif - Verifica se a letra está na palavra sorteada
         *      True -> altera os '_' pela letra e contabiliza o acerto;
         *      False -> Contabiliza o erro e atualiza 'drow' conforme as 'vidas' restantes.
        */
        boolean jaUsada = false;
        for (char c : this.usadas) {
            if (c == p) {
                jaUsada = true;
                break;
            }
        }
        if (!jaUsada){
            if (resposta.indexOf(p) != -1){
                for(int i = 0; i < this.len; i++)
                    if (this.resposta.charAt(i) == p){
                        this.tentativa[i] = p;
                        this.player.acerto();
                    }
            } else {
                player.erro();
                switch (this.player.vidas){
                    case 0:
                        this.drow[4] = " |   / \\";
                        break;
                    case 1:
                        this.drow[4] = " |   /";
                        break;
                    case 2:
                        this.drow[3] = " |   /|\\";
                        break;
                    case 3:
                        this.drow[3] = " |   /|";
                        break;
                    case 4:
                        this.drow[3] = " |    |";
                        break;
                    case 5:
                        this.drow[2] = " |    O";
                        break;
                }
            }
            this.usadas[this.len2++] = p;
        }
    }
}