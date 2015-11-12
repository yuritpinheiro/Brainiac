package Brainiac;

import Brainiac.Neuronio;
import java.util.ArrayList;

public class Camada {
	private ArrayList<Neuronio> neuronios;

	public static final int ENTRADA = 0;
	public static final int OCULTA = 1;
	public static final int SAIDA = 2;

	public Camada(){}

	public Camada(int quantidadeNeuronios, int tipoCamada){
		Neuronio n;
		for (int i = 0; i < quantidadeNeuronios; i++) {
			n = new Neuronio(tipoCamada);
			neuronios.add(n);
		}
	}

	public int getTamanhaCamada(){
		return neuronios.size();
	}

	public ArrayList<Neuronio> getNeuronios(){
		return neuronios;
	}

	public void setNeuronios(ArrayList<Neuronio> neuronios){
		this.neuronios = neuronios;
	}

	public Neuronio getNeuronio(int i){
		return this.neuronios.get(i);
	}

	public void setNeuronio(Neuronio neuronio, int i){
		this.neuronios.set(i, neuronio);
	}
}
