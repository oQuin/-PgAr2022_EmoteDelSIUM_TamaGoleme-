import java.util.ArrayList;

public class Giocatore {
    private TamaGolem squadra[];
    private String nome;
    private int golemVivo;

    public Giocatore(String nome,int numeroGolem){
        this.nome=nome;
        squadra= new TamaGolem[numeroGolem];
    }

    public void setTamaGolem( String[] elementi, int salute){
        int i;
        for(i=0;i< squadra.length;i++){
            if(squadra[i]==null)
                break;
        }
        squadra[i]=new TamaGolem(salute, elementi);
        golemVivo=i;
    }
    public String getNome() {
        return nome;
    }
    public boolean hasAltriGolem(int max){
        if(golemVivo==max-1)
            if(!squadra[max-1].isAlive())
                return false;

        return true;
    }
    public boolean hasNullGolem(){
        boolean r=false;
        for(int i=0;i< squadra.length;i++)
            if(squadra[i] == null)
                r=true;
        return r;
    }
    public TamaGolem getGolem(int idx){
        return squadra[idx];
    }
    public int getGolemVivo() {
        return golemVivo;
    }
}
