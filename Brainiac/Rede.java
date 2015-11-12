package Brainiac;

import Brainiac.Camada;
import java.utils.ArrayList;

public class Rede {
	private int quantidadeCamadas;
	private Camada camadaEntrada;
	private Camada camadaSaida;
	private ArrayList<Camada> camadasOcultas;
	private MalhaPesos malhaPesos[];

	public Rede();

	public Rede(int quantidadeEntradas, int quantidadeSaidas, int quantidadeCamadas, int neuronioPorCamada[]){
		this.quantidadeCamadas = quantidadeCamadas;
		this.camadaEntrada = new Camada(quantidadeEntradas, Camada.CAMADA_ENTRADA);
		this.camadaSaida = new Camada(quantidadeSaidas, Camada.CAMADA_SAIDA));
		Camada camadaOculta;
		for (int i = 0; i < quantidadeCamadas; i++) {
			camadaOculta = new Camada(neuronioPorCamada[i], Camada.CAMADA_OCULTA));
			camadasOcultas.add(camadaOculta);
		}
		malhaPesos = new MalhaPesos[quantidadeCamadas - 1];
		for (int i = 0; i < malhaPesos.length(); ) {

		}
	}

	public double propagacao(ArrayList<double> entrada){
		double resultado;

		/* Propagação */

		return resultado;
	}

	public void retroPropagacao(){
		/* Backpropagation */
	}

	public void treinamento(conjuntoTreinameto, conjuntoValidacao, int epoca, double erro){
		/* Treinamento */
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
		this.camadasOcultas.set(camadaOculta, i);
	}
}
