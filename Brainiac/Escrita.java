package Brainiac;
import java.io.*;
public class Escrita {

	static BufferedWriter buffer_de_escrita;

	FileWriter arquivo;
	static String linha;
	Escrita(){}
	Escrita(String caminho){

		try{
		buffer_de_escrita = new BufferedWriter(new FileWriter(caminho));
		}catch (IOException e){}
	}

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

	public void escrever_erro(double erro, int size){

	try{
			BufferedWriter buffer_de_escrita = new BufferedWriter(new FileWriter(caminho));
			
			for(int i = 0; i < size; i++){
				String linha = 	erro[i]+"\n";
				buffer_de_escrita.append(linha + "\n");
			}
			buffer_de_escrita.close();
			 
		}catch(IOException e){}
	}
		
		
}
