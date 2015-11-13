package Brainiac;

import Brainiac.Camada;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Rede {
	private int quantidadeCamadas;
	private Camada camadaEntrada;
	private Camada camadaSaida;
	private ArrayList<Camada> camadasOcultas;
	private MalhaPesos malhaPesos[];
	private ConjuntoDados conjuntoTreinameto;
	private ConjuntoDados conjuntoValidacao;

	public Rede(){
		/* Construtor vazio */
	}

	public Rede(int quantidadeCamadas,
				int neuronioPorCamada[],
				String conjuntoTreinameto,
				String conjuntoValidacao){
		this.quantidadeCamadas = quantidadeCamadas;
		this.camadaEntrada = new Camada(neuronioPorCamada[0], Camada.ENTRADA);
		this.camadaSaida = new Camada(neuronioPorCamada[quantidadeCamadas], Camada.SAIDA);
		Camada camadaOculta;
		for (int i = 0; i < quantidadeCamadas - 2 ; i++) {
			camadaOculta = new Camada(neuronioPorCamada[i], Camada.OCULTA);
			this.camadasOcultas.add(camadaOculta);
		}
		malhaPesos = new MalhaPesos[quantidadeCamadas - 1];
		for (int i = 0; i < malhaPesos.length; i++) {
			this.malhaPesos[i] = new MalhaPesos(neuronioPorCamada[i + 1], neuronioPorCamada[i]);
			this.malhaPesos[i].inicializar();
		}
		this.conjuntoTreinameto = new ConjuntoDados();
		this.conjuntoTreinameto.carregarDados(conjuntoTreinameto, neuronioPorCamada[0]);
		this.conjuntoValidacao = new ConjuntoDados();
		this.conjuntoValidacao.carregarDados(conjuntoValidacao, neuronioPorCamada[0]);
	}

	/* Propagação */
	public double propagacao(Amostra entrada){
		double resultado = 0;
		
		return resultado;
	}

	public void retroPropagacao(){
		/* Backpropagation */
	}

	public void treinamento(ConjuntoDados conjuntoTreinameto,
	 						ConjuntoDados conjuntoValidacao,
							int epoca,
							double erro){
		/* Treinamento */
	}

	public void salvarRede(){
		/* escrever em arquivo */
		/*
			FileWriter escritor = new FileWriter(fonte);
			PrintWriter saida = new PrintWriter(escritor, true);

			saida.close();
			escritor.close();
		*/
	}

	public void carregarRede(){
		/* Carrega Rede */
	}

	/* Gets e Sets */
	public int getQuantidadeCamadas(){
		return this.quantidadeCamadas;
	}

	public void setQuantidadeCamadas(int quantidadeCamadas){
		this.quantidadeCamadas = quantidadeCamadas;
	}

	public Camada getCamadaEntrada(){
		return this.camadaEntrada;
	}

	public void setCamadaEntrada(Camada camadaEntrada){
		this.camadaEntrada = camadaEntrada;
	}

	public Camada getCamadaSaida(){
		return this.camadaSaida;
	}

	public void setCamadaSaida(Camada camadaSaida){
		this.camadaSaida = camadaSaida;
	}

	public ArrayList<Camada> getCamadasOcultas(){
		return this.camadasOcultas;
	}

	public void setCamadasOcultas(ArrayList<Camada> camadasOcultas){
		this.camadasOcultas = camadasOcultas;
	}

	public Camada getCamadaOculta(int i){
		return this.camadasOcultas.get(i);
	}

	public void setCamadaOculta(Camada camadaOculta,int i){
		this.camadasOcultas.set(i, camadaOculta);
	}
}
