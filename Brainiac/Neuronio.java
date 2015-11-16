package Brainiac;

public class Neuronio {
	private int funcaoAtivacao;
	private double saida_neuronio;

	public static final int ENTRADA = 0;
	public static final int OCULTO = 1;
	public static final int SAIDA = 2;

	public Neuronio(){}

	public Neuronio(int tipoNeuronio, int funcaoAtivacao){
		this.funcaoAtivacao = funcaoAtivacao;
	}

	public double ativacao(double potencial){
		double resultado = Double.NaN;
		switch (funcaoAtivacao) {
			case FuncaoAtivacao.DEG:
				resultado = FuncaoAtivacao.degrau(potencial);
				break;
			case FuncaoAtivacao.LIN:
				resultado = FuncaoAtivacao.linear(potencial);
				break;
			case FuncaoAtivacao.SIG:
				resultado = FuncaoAtivacao.sigmoide(potencial);
				break;
			case FuncaoAtivacao.TAN:
				resultado = FuncaoAtivacao.tangenteHiperbolica(potencial);
				break;
			case FuncaoAtivacao.SIG_HEITOR:
				resultado = FuncaoAtivacao.sigmoideHeitor(potencial);
				break;
		}
		return resultado;
	}

	public int getFuncaoAtivacao(){
		return this.funcaoAtivacao;
	}

	public void setFuncaoAtivacao(int funcaoAtivacao){
		this.funcaoAtivacao = funcaoAtivacao;
	}
}
