package Brainiac;

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

	public void setPeso(int neuronioSeguinte, int neuronioAnterior, double peso){
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

	public void print(){
		for (int i = 0; i < tamanhoCamadaSeguinte; i++) {
			for (int j = 0; j < tamanhoCamadaAnterior; j++) {
				System.out.println("\tPeso do neurônio " + j + " para o neurônio " + (i + 1) + ": " + pesos[(i * tamanhoCamadaSeguinte) + j]);
			}
		}
	}

	@Override
	public String toString(){
		String stringPesos = new String();
		for (double peso : pesos){
			stringPesos += peso + "\t";
		}
		return stringPesos;
	}

}
