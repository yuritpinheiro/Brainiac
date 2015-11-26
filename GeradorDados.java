import Brainiac.Escrita;

import java.math.BigDecimal;
import java.util.Random;

public class GeradorDados {

	static Random rand = new Random();

	static public double elemento = 0;
	static public double passo_entrada = 0.015625;
	static public double passo_validacao = 0.25;
	static public double rangeMax = 15.0;
	static double[] conjunto_entrada = new double[(int)(rangeMax/passo_entrada) + 1];
	static double[] conjunto_validacao = new double[(int)(rangeMax/passo_validacao) + 1] ;
	static int size_entrada = 0;
	static int size_validacao = 0;
	static int size_total = 20;

	public static void main(String[] args) {

		Escrita escrita = new Escrita ();

		size_entrada = gerar_conjunto_entrada_dois();

		double[] y = new double[size_entrada];
		double[] z = new double[size_entrada];

		//Primeira Funcao
		for(int i = 0; i < size_entrada; i++){
			y[i] = 0.4 + 0.1*Math.exp(-5.0*conjunto_entrada[i]) - 0.5*Math.exp(-1.0*conjunto_entrada[i]);
		}

		elemento = 0;

		//Seguunda Funcao
		for(int i = 0; i < size_entrada; i++){
			z[i] = conjunto_entrada[i] * Math.exp(-2.0*(conjunto_entrada[i]));
		}

		escrita.escrever_amostra("Dados/funcao_um_treino.conjunto", conjunto_entrada, y, size_entrada);
		escrita.escrever_amostra("Dados/funcao_dois_treino.conjunto", conjunto_entrada, z, size_entrada);

		//validacao um
		size_validacao = gerar_conjunto_validacao_dois();

		for(int i = 0; i < size_validacao; i++)
			y[i] = 0.4 + 0.1*Math.exp(-5.0*conjunto_validacao[i]) - 0.5*Math.exp(-1.0*conjunto_validacao[i]);

		//validacao dois
		for(int i = 0; i < size_validacao; i++)
			z[i] = conjunto_validacao[i]*Math.exp(-2.0*(conjunto_validacao[i]));

		escrita.escrever_amostra("Dados/funcao_um_val.conjunto",  conjunto_validacao, y, size_validacao);
		escrita.escrever_amostra("Dados/funcao_dois_val.conjunto",conjunto_validacao, z, size_validacao);

	}

	public static int gerar_conjunto_entrada(){
		int count_passos = 0;
		while(elemento <= 15){
			conjunto_entrada[count_passos] = elemento;
			elemento += passo_entrada;
			count_passos++;
		}
		return count_passos;
	}

	public static int gerar_conjunto_entrada_dois(){
		int count_passos = 0;
		while(elemento <= 5){
			conjunto_entrada[count_passos] = elemento;
			elemento += passo_entrada;
			count_passos++;
		}

		while(elemento <= 15){
			conjunto_entrada[count_passos] = elemento;
			elemento += passo_entrada * 8;
			count_passos++;
		}
		return count_passos;
	}

	public static int gerar_conjunto_validacao(){
		int count_passos = 0;
		while(elemento <= 15){
			conjunto_validacao[count_passos] = elemento;
			elemento += passo_validacao;
			count_passos++;
		}
		return count_passos;
	}

	public static int gerar_conjunto_validacao_dois(){
		int count_passos = 0;
		while(elemento <= 5){
			conjunto_validacao[count_passos] = elemento;
			elemento += passo_validacao;
			count_passos++;
		}
		while(elemento <= 15){
			conjunto_validacao[count_passos] = elemento;
			elemento += passo_validacao * 2;
			count_passos++;
		}
		return count_passos;
	}

	public static  boolean ta_na_lista(double [] vetor, double elemento){

		for(int i = 0; i< vetor.length; i++){
			if(vetor[i] == elemento){
				return true;
			}
		}
		return false;
	}

}
