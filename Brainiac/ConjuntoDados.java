package Brainiac;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConjuntoDados{
	private ArrayList<Amostra> amostras;

	public void carregarDados(String caminho, int quantidadeEntradas){
		File fonte = new File(caminho);
		if (!fonte.exist() || !fonte.isFile()){
			return;
		}

		FileReader leitor = new FileReader(fonte);
		BufferedReader bufferLeitura = new BufferedReader(leitor);

		String linha = null;
		while((linha = bufferLeitura.readLine()){
			// converter linha para amostra
			amostras.add(amostra);
		}

		bufferLeitura.close();
		leitor.close();
	}
/* escrever em arquivo */
/*
	FileWriter escritor = new FileWriter(fonte);
	PrintWriter saida = new PrintWriter(escritor, true);

	saida.close();
	escritor.close();
*/
}
