package Brainiac;

import java.utils.Arraylist;

public class MalhaPesos {
	private int tamanhoCamadaSeguinte;
	private int tamanhoCamadaAnterior;
	private double pesos[];

	public MalhaPesos();

	public MalhaPesos(int tamanhoCamadaSeguinte, int tamanhoCamadaAnterior){
		pesos = new double[tamanhoCamadaSeguinte * tamanhoCamadaAnterior];
	}

	public double getPeso(int neuronioSeguinte, int neuronioAnterior){
		return pesos[neuronioSeguinte * peso.length() + neuronioAnterior];
	}

	public void setPeso(int neuronioSeguinte, int neuronioAnterior, int peso){
		pesos[neuronioSeguinte * peso.length() + neuronioAnterior] = peso;
	}

}
