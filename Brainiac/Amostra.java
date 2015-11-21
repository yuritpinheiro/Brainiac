package Brainiac;

public class Amostra{
	private double entradas[];

	public Amostra(int quantidadeEntradas){
		this.entradas = new double[quantidadeEntradas];
	}

	public double getEntrada(int i){
		return this.entradas[i];
	}

	public void setEntrada(int i, double entrada){
		this.entradas[i] = entrada;
	}

	public void setEntradas(double entradas[]){
		this.entradas = entradas;
	}

	public double getTamanhoAmostra(){
		return this.entradas.length;
	}
}
