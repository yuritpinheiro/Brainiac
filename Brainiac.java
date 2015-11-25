import Brainiac.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

class Brainiac {

	private static int epoca = 100;
	private static double erro = 0.00000000001;
	private static double n = 0.005;




	public static void main(String[] args) {

		/* Função 1 Arquitetura 1 */
		System.out.println("Função 1 Arquitetura 1 - Inicio");
		Rede r = new Rede();
		r.carregarRede("Redes/arq_um.rede");
		r.setTreino("funcao_um_arq_um");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_um_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());

		try{
			FileWriter escritor = new FileWriter("Treinamentos/funcao_um_arq_um_aprox.funcao");
			PrintWriter saida = new PrintWriter(escritor, true);

			Amostra a = new Amostra(2);
			a.setEntrada(0, 1);
			for (int i = 0; i < 150; i++) {
				a.setEntrada(1, i * 0.1);
				saida.println(r.propagacao(a)[0]);
			}

			saida.close();
			escritor.close();
		} catch (Exception e){
			System.out.println("Erro no salvamento da f1a1: " + e.getMessage());
		}

		System.out.println("Função 1 Arquitetura 1 - Fim");

		/* Função 1 Arquitetura 2 */
		System.out.println("Função 1 Arquitetura 2 - Inicio");
		r = new Rede();
		r.carregarRede("Redes/arq_dois.rede");
		r.setTreino("funcao_um_arq_dois");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_um_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());
		System.out.println("Função 1 Arquitetura 2 - Fim");

		/* Função 1 Arquitetura 3 */
		System.out.println("Função 1 Arquitetura 3 - Inicio");
		r = new Rede();
		r.carregarRede("Redes/arq_tres.rede");
		r.setTreino("funcao_um_arq_tres");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_um_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());
		System.out.println("Função 1 Arquitetura 3 - Fim");

		/* Função 2 Arquitetura 1 */
		System.out.println("Função 2 Arquitetura 1 - Inicio");
		r = new Rede();
		r.carregarRede("Redes/arq_um.rede");
		r.setTreino("funcao_dois_arq_um");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_dois_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());
		System.out.println("Função 2 Arquitetura 1 - Fim");

		/* Função 2 Arquitetura 2 */
		System.out.println("Função 2 Arquitetura 2 - Inicio");
		r = new Rede();
		r.carregarRede("Redes/arq_dois.rede");
		r.setTreino("funcao_dois_arq_dois");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_dois_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());
		System.out.println("Função 2 Arquitetura 2 - Fim");

		/* Função 2 Arquitetura 3 */
		System.out.println("Função 2 Arquitetura 3 - Inicio");
		r = new Rede();
		r.carregarRede("Redes/arq_tres.rede");
		r.setTreino("funcao_dois_arq_tres");
		r.inicializarPesos();
		r.carregarConjuntos("Dados/funcao_dois_treino.conjunto", "Dados/funcao_um_val.conjunto");
		r.treinamento(epoca, erro, n);
		r.salvarRede(r.getTreino());
		System.out.println("Função 2 Arquitetura 3 - Fim");
	}
}
