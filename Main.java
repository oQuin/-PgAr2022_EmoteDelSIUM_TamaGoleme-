import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main 
{
	private static final int NUM_ELEMENTI = 6;
	private static final int NUM_PIETRE = ((NUM_ELEMENTI + 1) / 3) + 1;
	private static final int NUM_TAMAGOLEM = ((NUM_ELEMENTI - 1) * (NUM_ELEMENTI - 2))/(2 * NUM_PIETRE) ;	//3
	private static final int NUM_PIETRE_SCORTA = ((2 * NUM_TAMAGOLEM * NUM_PIETRE) / NUM_ELEMENTI)* NUM_ELEMENTI;	//pool comune 18
	private static final int SALUTE_MAX = 20;
	private static final int NUM_PIETRE_PER_ELEMENTO = NUM_PIETRE_SCORTA/NUM_ELEMENTI;
	private static String ELEMENTI[] = new String [NUM_ELEMENTI];
	private static String POOL_ELEMENTI[] = new String [NUM_PIETRE_SCORTA];


	public static void main(String[] args)
	{

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
		//equilibrio_elementi.stampa();
		int matriceEquilibrio[][]=equilibrio_elementi.getMatriceDiEquilibrio();
		//Fase 2: Scontro
		Giocatore giocatori[] = {new Giocatore("Andre del 1988 Generale dell'esercito delle papere",NUM_TAMAGOLEM),new Giocatore("Leonardo Maini Barbieri Principe del Gran ducato dei Savoia",NUM_TAMAGOLEM)};
		int start = (int) Math.round( Math.random());

		int scn = start==1?0:1;

		generaGolem(giocatori[start]);
		generaGolem(giocatori[scn]);

		do{
			//genera golem

			if(!giocatori[start].getGolem(giocatori[start].getGolemVivo()).isAlive()){
				generaGolem(giocatori[start]);
			}

			if(!giocatori[scn].getGolem(giocatori[scn].getGolemVivo()).isAlive()){
				generaGolem(giocatori[scn]);
			}

			TamaGolem golemPrimo = giocatori[start].getGolem(giocatori[start].getGolemVivo());
			TamaGolem golemSecon = giocatori[scn].getGolem(giocatori[scn].getGolemVivo());
			do{
				String attaccoTamaPrimo=golemPrimo.attacca();
				String attaccoTamaSecon=golemSecon.attacca();

				int indiceTabellaPrimo = java.util.Arrays.asList(ELEMENTI).indexOf(attaccoTamaPrimo);
				int indiceTabellaSecon = java.util.Arrays.asList(ELEMENTI).indexOf(attaccoTamaSecon);

				int dannoPrimoSuSecondo = matriceEquilibrio[indiceTabellaPrimo][indiceTabellaSecon];
				int dannoSecondoSuPrimo = matriceEquilibrio[indiceTabellaSecon][indiceTabellaSecon];
				if(dannoPrimoSuSecondo>dannoSecondoSuPrimo){
					golemSecon.getDamaged(dannoPrimoSuSecondo);
				}else{
					golemPrimo.getDamaged(dannoSecondoSuPrimo);
				}

			}while(golemPrimo.isAlive() && golemSecon.isAlive());


			System.out.println("ciao");

		}while(giocatori[start].hasAltriGolem() && giocatori[scn].hasAltriGolem());

		//Fase 3: Dichiarazione del vincitore
	    
	} 		

	private static void generaGolem(Giocatore a){
		String elementiPerTamaGolem[]=new String[NUM_PIETRE];
		System.out.println("Scegli tre pietre per il Golem di "+a.getNome()+" (ex 1 2 3)");
		for(int j =0;j<POOL_ELEMENTI.length;j++)
			System.out.println((j+1)+") "+POOL_ELEMENTI[j]);
		Scanner sc= new Scanner(System.in);
		int scelte[]=new int[NUM_PIETRE];
		for(int j=0;j<NUM_PIETRE;j++){
			scelte[j] = sc.nextInt()-1;
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
		a.setTamaGolem(elementiPerTamaGolem,SALUTE_MAX);
	}
}


/*
System.out.println("ora tocca al giocatore " + giocatori[start].getNome());
		for(int i=0;i<NUM_TAMAGOLEM;i++){
			String elementiPerTamaGolem[]=new String[NUM_PIETRE];
			System.out.println("Scegli tre pietre per il Golem nuemro "+i+" (ex 1 2 3)");
			for(int j =0;j<POOL_ELEMENTI.length;j++)
				System.out.println((j+1)+") "+POOL_ELEMENTI[j]);
			Scanner sc= new Scanner(System.in);
			int scelte[]=new int[NUM_PIETRE];
			for(int j=0;j<NUM_PIETRE;j++){
				scelte[j] = sc.nextInt()-1;
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
 */