import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		final int NUM_ELEMENTI = 6;
		final int NUM_PIETRE = ((NUM_ELEMENTI + 1) / 3) + 1;	//per golem (3)
		final int NUM_TAMAGOLEM = ((NUM_ELEMENTI - 1) * (NUM_ELEMENTI - 2))/(2 * NUM_PIETRE) ;	//3
		final int NUM_PIETRE_SCORTA = ((2 * NUM_TAMAGOLEM * NUM_PIETRE) / NUM_ELEMENTI)* NUM_ELEMENTI;	//pool comune 18
		final int SALUTE_MAX = 20;
		final int NUM_PIETRE_PER_ELEMENTO = NUM_PIETRE_SCORTA/NUM_ELEMENTI;
		String ELEMENTI[] = new String [NUM_ELEMENTI];
		String POOL_ELEMENTI[] = new String [NUM_PIETRE_SCORTA];

		int elemento = 97; //equivalente di a minuscola in Unicode
		for(int i=0;i<NUM_ELEMENTI;i++){
			ELEMENTI[i] = String.valueOf((char)elemento);
			elemento++;
		}
		for(int i=0;i<NUM_PIETRE_SCORTA;i++){
			int indiceAssegnazioneElementi= i/NUM_PIETRE_PER_ELEMENTO;
			POOL_ELEMENTI[i]=ELEMENTI[indiceAssegnazioneElementi];
		}



		ArrayList<Nodo> lista_elementi = new ArrayList<Nodo>();
		
		//Fase 1: Creazione dell'equilibrio(da sistemare)
		for(int i=0;i<NUM_ELEMENTI;i++)
			lista_elementi.add(new Nodo(ELEMENTI[i]));
		
		Grafo equilibrio_elementi = new Grafo(lista_elementi);		
		equilibrio_elementi.setEquilibrio(SALUTE_MAX);
		equilibrio_elementi.stampa();
		
		//Fase 2: Scontro
		//Creo Giocatori
		Giocatore giocatori[] = {new Giocatore("a",NUM_TAMAGOLEM),new Giocatore("b",NUM_TAMAGOLEM)};
		int start = (int) Math.round( Math.random());

		//Creazione e scelta pietre primo giocatore
		System.out.println("inzia il giocatore " + giocatori[start].getNome());
		for(int i=0;i<NUM_TAMAGOLEM;i++){
			String elementiPerTamaGolem[]=new String[NUM_PIETRE];
			System.out.println("Scegli tre pietre per il Golem nuemro "+i+" (ex 1 2 3)");
			for(int j =0;j<POOL_ELEMENTI.length;j++)
				System.out.println((j+1)+") "+POOL_ELEMENTI[j]);
			Scanner sc= new Scanner(System.in);
			int scelte[]=new int[NUM_PIETRE];
			for(int j=0;j<NUM_PIETRE;j++){
				scelte[j] = sc.nextInt();
				if (scelte[j]<0 || scelte[j]> POOL_ELEMENTI.length-1){
					System.out.println("Scegliere tra i numeri stampati a video");
					j--;
				}
			}
			for(int j=0;j<NUM_PIETRE;j++){
				elementiPerTamaGolem[j]=POOL_ELEMENTI[scelte[j]];
				POOL_ELEMENTI[scelte[j]]=null;
			}
			POOL_ELEMENTI= Arrays.stream(POOL_ELEMENTI).filter(Objects::nonNull).toArray(String[]::new);
			giocatori[start].setTamaGolem(i,elementiPerTamaGolem,SALUTE_MAX);
		}

		//Creazione e scelta pietre secondo giocatore


		
		//Fase 3: Dichiarazione del vincitore
		
		
	    
	} 		
	
	

	

}
