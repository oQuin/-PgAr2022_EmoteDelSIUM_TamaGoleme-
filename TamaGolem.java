import java.util.LinkedList;
import java.util.Queue;

public class TamaGolem
{
	private int salute;
	private Queue<String> coda= new LinkedList<String>();
	private String elementi[];

	public TamaGolem(int s, String[] elementi)
	{
		salute=s;
		this.elementi=elementi;
	}
}
