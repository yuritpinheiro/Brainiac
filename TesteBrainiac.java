
import Brainiac.FuncaoAtivacao;
import Brainiac.Neuronio;
import Brainiac.MalhaPesos;
import Brainiac.ConjuntoDados;
import Brainiac.Amostra;
import Brainiac.Rede;

public class TesteBrainiac{
	public static void main(String args[]){
		// testeAtivacao();
		// testeNeuronio();
		// testeMalhaPesos();
		// testeConjuntoDados(); // Amostra
		// testeRede();
		testePropagacao();
	}

	/* Teste Rede */
	public static void testeRede(){
		Rede rede = new Rede();
		rede.carregarRede("exemplo.rede");
		rede.print();
	}

	/* Teste Propagação */
	public static void testePropagacao(){
		Rede rede = new Rede();
		rede.carregarRede("exemplo.rede");
		rede.print();
		Amostra dados = new Amostra(3);
		double entradas[] = {1, 0, 0};
		dados.setEntradas(entradas);
		System.out.println("Saida (0,0): " + rede.propagacao(dados)[0]);
		entradas[2] = 1;
		dados.setEntradas(entradas);
		System.out.println("Saida (0,1): " + rede.propagacao(dados)[0]);
		entradas[1] = 1;
		entradas[2] = 0;
		dados.setEntradas(entradas);
		System.out.println("Saida (1,0): " + rede.propagacao(dados)[0]);
		entradas[1] = 1;
		entradas[2] = 1;
		dados.setEntradas(entradas);
		System.out.println("Saida (1,1): " + rede.propagacao(dados)[0]);
	}

	/* Teste Treinamento */
	public static void testeTreinamento(){

	}

	/* Teste RetroPropagação */
	public static void testeRetroPropagação(){

	}

	/* Teste ConjuntoDados e Amostra */
	public static void testeConjuntoDados(){
		ConjuntoDados conjunto = new ConjuntoDados();

		conjunto.carregarDados("exemplo.conjunto", 4);

		while(conjunto.terminado()){
			Amostra amostra;
			amostra = conjunto.proximaAmostra();
			for (int i = 0; i < 4; i++) {
				System.out.print(amostra.getEntrada(i) + " ");
			}
			System.out.print("\n");
		}
	}

	/* Teste MalhaPesos */
	public static void testeMalhaPesos(){
		MalhaPesos malha = new MalhaPesos(2, 2);

		malha.setPeso(0, 0, 1);
		malha.setPeso(0, 1, 2);
		malha.setPeso(1, 0, 3);
		malha.setPeso(1, 1, 4);

		malha.print();

		System.out.println(malha.getPeso(0, 0));
		System.out.println(malha.getPeso(0, 1));
		System.out.println(malha.getPeso(1, 0));
		System.out.println(malha.getPeso(1, 1));

		malha.inicializar();
		malha.print();

		System.out.println(malha.getPeso(0, 0));
		System.out.println(malha.getPeso(0, 1));
		System.out.println(malha.getPeso(1, 0));
		System.out.println(malha.getPeso(1, 1));
	}

	/* Teste Neurônios */
	public static void testeNeuronio(){
		System.out.println("Criar neurônio com ativação degrau");
		Neuronio n = new Neuronio(1, FuncaoAtivacao.DEG);
		n.setPotencial(1);
		System.out.println(n.ativacao());
		n.setPotencial(0);
		System.out.println(n.ativacao());
		n.setPotencial(-1);
		System.out.println(n.ativacao());
		System.out.println("Criar neurônio com ativação linear");
		n = new Neuronio(1, FuncaoAtivacao.LIN);
		n.setPotencial(1);
		System.out.println(n.ativacao());
		n.setPotencial(0);
		System.out.println(n.ativacao());
		n.setPotencial(-1);
		System.out.println(n.ativacao());
		System.out.println("Criar neurônio com ativação sigmoide");
		n = new Neuronio(1, FuncaoAtivacao.SIG);
		n.setPotencial(1);
		System.out.println(n.ativacao());
		n.setPotencial(0);
		System.out.println(n.ativacao());
		n.setPotencial(-1);
		System.out.println(n.ativacao());
		System.out.println("Criar neurônio com ativação tangente hiperbolica");
		n = new Neuronio(1, FuncaoAtivacao.TAN);
		n.setPotencial(1);
		System.out.println(n.ativacao());
		n.setPotencial(0);
		System.out.println(n.ativacao());
		n.setPotencial(-1);
		System.out.println(n.ativacao());
	}

	/* Teste Funções de ativação */
	public static void testeAtivacao(){
		testeAtivacaoDegrau();
		testeAtivacaoLinear();
		testeAtivacaoSigmoide();
		testeAtivacaoTangenteHiperbolica();
	}

	public static void testeAtivacaoSigmoide(){
		System.out.println("sigmoide");
		System.out.println(FuncaoAtivacao.sigmoide(1));
		System.out.println(FuncaoAtivacao.sigmoide(0));
		System.out.println(FuncaoAtivacao.sigmoide(-1));
		System.out.println("derivadaSigmoide");
		System.out.println(FuncaoAtivacao.derivadaSigmoide(1));
		System.out.println(FuncaoAtivacao.derivadaSigmoide(0));
		System.out.println(FuncaoAtivacao.derivadaSigmoide(-1));
	}

	public static void testeAtivacaoTangenteHiperbolica(){
		System.out.println("tangenteHiperbolica");
		System.out.println(FuncaoAtivacao.tangenteHiperbolica(1));
		System.out.println(FuncaoAtivacao.tangenteHiperbolica(0));
		System.out.println(FuncaoAtivacao.tangenteHiperbolica(-1));
		System.out.println("derivadaTangenteHiperbolica");
		System.out.println(FuncaoAtivacao.derivadaTangenteHiperbolica(1));
		System.out.println(FuncaoAtivacao.derivadaTangenteHiperbolica(0));
		System.out.println(FuncaoAtivacao.derivadaTangenteHiperbolica(-1));
	}

	public static void testeAtivacaoDegrau(){
		System.out.println("degrau");
		System.out.println(FuncaoAtivacao.degrau(1));
		System.out.println(FuncaoAtivacao.degrau(0));
		System.out.println(FuncaoAtivacao.degrau(-1));
		System.out.println("derivadaDegrau");
		System.out.println(FuncaoAtivacao.derivadaDegrau(1));
		System.out.println(FuncaoAtivacao.derivadaDegrau(0));
		System.out.println(FuncaoAtivacao.derivadaDegrau(-1));
	}

	public static void testeAtivacaoLinear(){
		System.out.println("linear");
		System.out.println(FuncaoAtivacao.linear(1));
		System.out.println(FuncaoAtivacao.linear(0));
		System.out.println(FuncaoAtivacao.linear(-1));
		System.out.println("derivadaLinear");
		System.out.println(FuncaoAtivacao.derivadaLinear(1));
		System.out.println(FuncaoAtivacao.derivadaLinear(0));
		System.out.println(FuncaoAtivacao.derivadaLinear(-1));
	}
}
