package Brainiac;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConjuntoDados{
	private ArrayList<Amostra> amostras;
	private int contadorDados;

	public ConjuntoDados(){
		this.amostras = new ArrayList<Amostra>();
		this.contadorDados = 0;
	}

	public void carregarDados(String caminho, int quantidadeEntradas){
		try{
			File fonte = new File(caminho);
			if (!fonte.exists() || !fonte.isFile()){
				return;
			}

			FileReader leitor = new FileReader(fonte);
			BufferedReader bufferLeitura = new BufferedReader(leitor);

			String linha = null, dados[];
			Amostra amostra;
			while(bufferLeitura.ready()){
				linha = bufferLeitura.readLine();
				dados = linha.split("\t");
				amostra = new Amostra(quantidadeEntradas);
				int i = 0;
				for (String dado : dados){
					amostra.setEntrada(i, Double.valueOf(dado));
					i++;
				}
				amostras.add(amostra);
			}

			bufferLeitura.close();
			leitor.close();
		} catch (Exception e){
			System.out.println("Erro Conjunto: " + e.getMessage());
		}
	}

	public Amostra proximaAmostra(){
		return amostras.get(contadorDados++);
	}

	public void resetarContador(){
		contadorDados = 0;
	}

	public boolean terminado(){
		return (contadorDados < amostras.size());
	}

	public int getTamanhoDados(){

		return this.amostras.size();
	}

	public void embaralhar(){
		Collections.shuffle(amostras);
	}
}
