import java.util.LinkedList;
import java.util.Queue;

public class TamaGolem
{
	private int salute;
	private Queue<String> coda= new LinkedList<String>();
	private String elementi[];
	private boolean vivo;

	public TamaGolem(int s, String[] elementi)
	{
		salute=s;
		this.elementi=elementi;
		vivo = true;
		for(int i=0;i< elementi.length;i++){
			coda.add(elementi[i]);
		}
	}

	public void getDamaged(int danno){
		salute -= danno;
		if (salute <= 0) vivo = false;
	}

	public boolean isAlive(){
		return vivo;
	}

	public String attacca(){
		coda.add(coda.peek());
		return coda.remove();
	}
}
