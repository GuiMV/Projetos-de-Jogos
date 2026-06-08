import java.util.Random;

public class Player{
    private final String Name;
    private int Win;
    private String Obj;
    private static final Random RNG = new Random();
    private static final String[] opcoes = {"Pedra", "Papel", "Tesoura"};
    
    public Player(String nome){
        this.Name = nome;
        this.Win = 0;
    }
    
    public String getName(){
        return this.Name;
    }
    public int getWin(){
        return this.Win;
    }
    public String getObj(){
        return this.Obj;
    }
    
    public void incrementWin(){
        this.Win++;
    }
    public void jogada(){
        this.Obj = opcoes[RNG.nextInt(3)];
    }
    public void setObj(String obj){
        this.Obj = obj;
    }
}