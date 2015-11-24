import Brainiac.*;

class Brainiac {

	private static int epoca = 100000;
	private static double erro = 0.0001;
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
