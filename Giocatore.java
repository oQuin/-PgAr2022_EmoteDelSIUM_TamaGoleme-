import java.util.ArrayList;

public class Giocatore {

    private boolean vincitore=false;
    private TamaGolem squadra[];
    private int golemVivi;
    private String nome;

    public Giocatore(String nome,int numeroGolem){
        this.nome=nome;
        squadra= new TamaGolem[numeroGolem];
    }

    public void setTamaGolem(int idx, String[] elementi, int salute){
        squadra[idx]=new TamaGolem(salute, elementi);
    }
    public String getNome() {
        return nome;
    }
}
