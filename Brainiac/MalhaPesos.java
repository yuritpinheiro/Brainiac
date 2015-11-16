package Brainiac;

import java.util.ArrayList;

public class MalhaPesos {
	private int tamanhoCamadaSeguinte;
	private int tamanhoCamadaAnterior;
	private double pesos[];

	public MalhaPesos(){

	}

	public MalhaPesos(int tamanhoCamadaSeguinte, int tamanhoCamadaAnterior){
		pesos = new double[tamanhoCamadaSeguinte * tamanhoCamadaAnterior];
		this.tamanhoCamadaSeguinte = tamanhoCamadaSeguinte;
		this.tamanhoCamadaAnterior = tamanhoCamadaAnterior;
	}

	public double getPeso(int neuronioSeguinte, int neuronioAnterior){
		return pesos[neuronioSeguinte * tamanhoCamadaSeguinte + neuronioAnterior];
	}

	public void setPeso(int neuronioSeguinte, int neuronioAnterior, int peso){
		pesos[neuronioSeguinte * tamanhoCamadaSeguinte + neuronioAnterior] = peso;
	}

	public void setPesos(double pesos[]){
		this.pesos = pesos;
	}

	public void inicializar(){
		for (int i = 0; i < pesos.length; i++) {
			pesos[i] = Math.random();
		}
	}

	public void printPesos(){
		for (int i = 0; i < pesos.length; i++) {
			System.out.println(pesos[i]);
		}
	}
}
