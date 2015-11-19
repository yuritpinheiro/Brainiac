import java.math.BigDecimal;
import java.util.Random;
public class teste {
	
	static Random rand = new Random();
	
		
	static double[] conjunto_entrada = new double[15];
	static double[] conjunto_validacao = new double[5];
	static public double elemento = 0;
	static public double passo_entrada = 0.375; 
	static public double passo_validacao= 0.5;
	static int size_entrada = 15;
	static int size_validacao = 5;
	static int size_total = 20;

	public static void main(String[] args) {
		
		Escrita escrita = new Escrita ();
		
		double[] y = new double[15];
		double[] z = new double[15];		
		
		gerar_conjunto_entrada();

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
		gerar_conjunto_validacao();

		for(int i = 0; i < size_validacao; i++)
			y[i] = 0.4 + 0.1*Math.exp(-5.0*conjunto_validacao[i]) - 0.5*Math.exp(-1.0*conjunto_validacao[i]);		
		
		//validacao dois
		for(int i = 0; i < size_validacao; i++)
			z[i] = conjunto_validacao[i]*Math.exp(-2.0*(conjunto_validacao[i]));
	
		escrita.escrever("validacao_funcao_um",  conjunto_validacao, y, size_validacao);
		escrita.escrever("validacao_funcao_dois",conjunto_validacao, z, size_validacao);
			
	}

	public static void gerar_conjunto_entrada(){
		for(int i = 0; i < size_entrada; i++){

			conjunto_entrada[i] = elemento;
			elemento = (elemento + passo_entrada)%16;
			
		}
	}

	public static void gerar_conjunto_validacao(){
		for(int i = 0; i < size_validacao; i++){
			conjunto_validacao[i] = elemento;
			elemento = (elemento + (new BigDecimal(passo_validacao).setScale(2, 1).doubleValue())%16);
			
		}
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
