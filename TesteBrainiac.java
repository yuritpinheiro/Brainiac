
import Brainiac.FuncaoAtivacao;
import Brainiac.Neuronio;
import Brainiac.MalhaPesos;

public class TesteBrainiac{
	public static void main(String args[]){
		// testeAtivacao();
		// testeNeuronio();
		testeMalhaPesos();
	}

	/* Teste MalhaPesos */
	public static void testeMalhaPesos(){
		MalhaPesos malha = new MalhaPesos(2, 2);

		malha.setPeso(0, 0, 1);
		malha.setPeso(0, 1, 2);
		malha.setPeso(1, 0, 3);
		malha.setPeso(1, 1, 4);

		malha.printPesos();

		System.out.println(malha.getPeso(0, 0));
		System.out.println(malha.getPeso(0, 1));
		System.out.println(malha.getPeso(1, 0));
		System.out.println(malha.getPeso(1, 1));
	}

	/* Teste Neurônios */
	public static void testeNeuronio(){
		System.out.println("Criar neurônio com ativação degrau");
		Neuronio n = new Neuronio(FuncaoAtivacao.DEG);
		System.out.println(n.ativacao(1));
		System.out.println(n.ativacao(0));
		System.out.println(n.ativacao(-1));
		System.out.println("Criar neurônio com ativação linear");
		n = new Neuronio(FuncaoAtivacao.LIN);
		System.out.println(n.ativacao(1));
		System.out.println(n.ativacao(0));
		System.out.println(n.ativacao(-1));
		System.out.println("Criar neurônio com ativação sigmoide");
		n = new Neuronio(FuncaoAtivacao.SIG);
		System.out.println(n.ativacao(1));
		System.out.println(n.ativacao(0));
		System.out.println(n.ativacao(-1));
		System.out.println("Criar neurônio com ativação tangente hiperbolica");
		n = new Neuronio(FuncaoAtivacao.TAN);
		System.out.println(n.ativacao(1));
		System.out.println(n.ativacao(0));
		System.out.println(n.ativacao(-1));
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
