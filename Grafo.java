import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom; // serve per generare numeri casuali

public class Grafo 
{
	private ArrayList<Nodo> elementi; 
	private int matAdiacenza[][];
	
	public Grafo(ArrayList<Nodo> l)
	{
		elementi = l;
		matAdiacenza = new int [l.size()][l.size()];
	}
	
	public void setAdiacenza(Nodo a, Nodo b, int peso)
	{
		int i = elementi.indexOf(a);
		int j = elementi.indexOf(b);
		
		matAdiacenza[i][j] = peso;
	}
	
	public int getSommaPesiRiga(int A[][],int indx, int dim) // ritorna la somma dei pesi degli archi uscenti da un nodo
	{
		int somma = 0;
		
		for(int i = 0; i < dim; i++)
			somma=somma+A[indx][i];
		
		return somma;
	}
	
	public int getSommaPesiColonna(int A[][],int indx, int dim) // ritorna la somma dei pesi degli archi entranti in un nodo
	{
		int somma = 0;
		
		for(int i=0; i<dim; i++) 
			somma=somma+A[i][indx];
		
		return somma;
	}
	
	public void setEquilibrio(int salute_massima)
	{
		int peso=0; 
		int cont;  //variabile contatore. Serve per contare quanti nodi vengono collegati a un altro nodo
		
		for(int i = 0; i < elementi.size()-1; i++)
		{
			cont = 0;
			
			
			for(int j = i + 1; j<elementi.size(); j++)
			{
				cont++;
				
				
				
				if(cont==elementi.size()-2-i) //Caso penultimo nodo da collegare
				{
					
					do //genero un valore casuale fintantoche' non vengono soddisfatte alcune condizioni. Se il numero casuale generato e' positivo, l'arco sara' entrante dall'i-esimo nodo e uscente dal j-esimo e viceversa...
					{
						peso = ThreadLocalRandom.current().nextInt(salute_massima * (-1), salute_massima+1);
					}while(peso==0 || (peso>0 && ((getSommaPesiRiga(matAdiacenza,i,cont+i)+peso) == getSommaPesiColonna(matAdiacenza,i,cont+i)))
							|| (peso<0 && (getSommaPesiRiga(matAdiacenza,i,cont+i) == (getSommaPesiColonna(matAdiacenza,i,cont+i)+Math.abs(peso))))
							|| (peso>0 && Math.abs(getSommaPesiRiga(matAdiacenza,i,cont+i)+peso - getSommaPesiColonna(matAdiacenza,i,cont+i))>salute_massima)
							|| (peso<0 && Math.abs(getSommaPesiRiga(matAdiacenza,i,cont+i) - ((getSommaPesiColonna(matAdiacenza,i,cont+i)+Math.abs(peso))))>salute_massima)
							|| (peso>0 && Math.abs(getSommaPesiColonna(matAdiacenza,elementi.size()-2,cont+i)+peso-getSommaPesiRiga(matAdiacenza,elementi.size()-2,cont+i))>salute_massima)
							|| (peso<0 && Math.abs(getSommaPesiRiga(matAdiacenza,elementi.size()-2,cont+i)+Math.abs(peso)-getSommaPesiColonna(matAdiacenza,elementi.size()-2,cont+i))>salute_massima)
							|| (peso>0 && ((getSommaPesiColonna(matAdiacenza,elementi.size()-2,cont+i)+peso) == getSommaPesiRiga(matAdiacenza,elementi.size()-2,cont+i)))
							|| (peso<0 && ((getSommaPesiRiga(matAdiacenza,elementi.size()-2,cont+i)+Math.abs(peso)) == getSommaPesiColonna(matAdiacenza,elementi.size()-2,cont+i))));
					
					//1) peso diverso da 0 (dato che l'interazione tra due elementi diversi non è mai nulla)
					//2) la somma tra i pesi degli archi entranti deve essere diversa da quella degli archi uscenti.
					// Il peso dell'ultimo arco non viene generato in maniera casuale, ma viene ricavato come differenza
					// assoluta tra la somma degli archi entranti e quella degli archi uscenti. Se questa condizione non
					// dovesse sussistere allora il peso dell'ultimo arco risulterebbe nullo (escluso)

					//3) la differenza tra la somma degli archi entranti e la somma degli archi uscenti deve essere
					// <=valore massimo vita TamaGolem. Se questa condizione non dovesse sussistere, l'ultimo arco dell'i-esimo nodo
					// avra' un peso maggiore del valore massimo della vita di un TamaGolem (escluso)

					//4) la differenza  tra la somma degli elementi della penultima riga della matrice di adiacenza e la somma
					// degli elementi della penultima colonna deve essere diversa da 0 e minore o uguale a 20. Serve per evitare
					// di avere valori nulli o maggiori di salute_massima nell'ultima riga o colonna
				}
				else if(cont==elementi.size()-1-i) //Caso ultimo nodo da collegare
				{
					if(getSommaPesiRiga(matAdiacenza,i,cont)<getSommaPesiColonna(matAdiacenza,i,cont))
					{
						peso=getSommaPesiColonna(matAdiacenza,i,cont+i)-getSommaPesiRiga(matAdiacenza,i,cont+i);
					}
					else
					{
						peso=getSommaPesiRiga(matAdiacenza,i,cont+i)-getSommaPesiColonna(matAdiacenza,i,cont+i);
						peso*=(-1);
					}
					
					//ricavo il peso dell'ultimo arco come differenza tra la somma dei pesi degli archi entranti e la somma dei pesi degli archi uscenti. Se la differenza e' positiva l'arco risultera' entrante dal i-esimo nodo e uscente dal j-esimo. E viceversa...					
				}
				else
				{
					
					do
					{
						peso = ThreadLocalRandom.current().nextInt(salute_massima * (-1), salute_massima+1);   //genera un numero casuale compreso tra (-SALUTE_MAX , SALUTE_MAX) ad eccezione dello zero
							
					}while(peso==0 || (peso>0 && Math.abs((getSommaPesiRiga(matAdiacenza,i,cont+i)+peso)-getSommaPesiColonna(matAdiacenza,i,cont+i))>salute_massima) || (peso<0 && Math.abs((getSommaPesiColonna(matAdiacenza,i,cont+i)+Math.abs(peso))-getSommaPesiRiga(matAdiacenza,i,cont+i))>salute_massima) || (peso>0 && Math.abs(getSommaPesiColonna(matAdiacenza,i+1,cont+i)+peso -getSommaPesiRiga(matAdiacenza,i+1,cont+i))>salute_massima) || (peso<0 && Math.abs(getSommaPesiRiga(matAdiacenza,i+1,cont+i)+Math.abs(peso)-getSommaPesiColonna(matAdiacenza,i+1,cont+i))>salute_massima));
					
				}
				
				if(peso>0)
					setAdiacenza(elementi.get(i),elementi.get(j),peso);
				else
					setAdiacenza(elementi.get(j),elementi.get(i),peso*(-1));
				
			}
			
		}
	}
	
	public void stampa() //metodo temporaneo: mi serve solo per verificare la correttezza del grafo
	{
		for(int i=0;i<matAdiacenza.length;i++)
		{
			for(int j=0;j<matAdiacenza[i].length;j++)
				System.out.print(matAdiacenza[i][j] + "\t");
			
			System.out.println();
		}
			
		
	}
}
