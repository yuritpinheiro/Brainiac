import java.math.BigDecimal;
import java.util.Random;
public class GeradorDados {

	static Random rand = new Random();



	static public double elemento = 0;
	static public double passo_entrada = 0.375;
	static public double passo_validacao= 0.5;
	static public double rangeMax = 15.0;
	static double[] conjunto_entrada = new double[(int)(rangeMax/passo_entrada) + 1];
	static double[] conjunto_validacao = new double[(int)(rangeMax/passo_validacao) + 1] ;
	static int size_entrada = 0;
	static int size_validacao = 0;
	static int size_total = 20;

	public static void main(String[] args) {

		Escrita escrita = new Escrita ();



		size_entrada = gerar_conjunto_entrada();

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

		escrita.escrever("treinamento_funcao_um", conjunto_entrada, y, size_entrada);
		escrita.escrever("treinamento_funcao_dois", conjunto_entrada, z, size_entrada);

		//validacao um
		size_validacao = gerar_conjunto_validacao();

		for(int i = 0; i < size_validacao; i++)
			y[i] = 0.4 + 0.1*Math.exp(-5.0*conjunto_validacao[i]) - 0.5*Math.exp(-1.0*conjunto_validacao[i]);

		//validacao dois
		for(int i = 0; i < size_validacao; i++)
			z[i] = conjunto_validacao[i]*Math.exp(-2.0*(conjunto_validacao[i]));

		escrita.escrever("validacao_funcao_um",  conjunto_validacao, y, size_validacao);
		escrita.escrever("validacao_funcao_dois",conjunto_validacao, z, size_validacao);

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

	public static int gerar_conjunto_validacao(){
		int count_passos = 0;
		while(elemento <= 15){
			conjunto_validacao[count_passos] = elemento;
			elemento += passo_validacao;
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
