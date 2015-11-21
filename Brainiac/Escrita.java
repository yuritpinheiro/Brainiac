package Brainiac;
import java.io.*;
public class Escrita {

	public void escrever_amostra(String caminho, double [] amostra, double [] saida, int size){
		int bias = -1;		
		try{
			BufferedWriter buffer_de_escrita = new BufferedWriter(new FileWriter(caminho));
			
			for(int i = 0; i < size; i++){
				String linha = 	bias+"\t"+amostra[i]+"\t"+saida[i];
				buffer_de_escrita.append(linha + "\n");
			}
			buffer_de_escrita.close();
			 
		}catch(IOException e){}
		
	}

	public void escrever_erro(String caminho, double erro, int size){
		int bias = -1;		
		try{
			BufferedWriter buffer_de_escrita = new BufferedWriter(new FileWriter(caminho));
			
			for(int i = 0; i < size; i++){
				String linha = 	erro+"\n";
				buffer_de_escrita.append(linha);
			}
			buffer_de_escrita.close();
			 
		}catch(IOException e){}
		
	}
}
