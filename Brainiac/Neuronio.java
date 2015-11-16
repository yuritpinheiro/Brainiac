package Brainiac;

public class Neuronio {
	private int funcaoAtivacao;
	private double potencial;

	public static final int ENTRADA = 0;
	public static final int OCULTO = 1;
	public static final int SAIDA = 2;

	public Neuronio(){}

	public Neuronio(int tipoNeuronio, int funcaoAtivacao){
		this.funcaoAtivacao = funcaoAtivacao;
	}

	public double ativacao(){
		double resultado = Double.NaN;
		switch (funcaoAtivacao) {
			case FuncaoAtivacao.DEG:
				resultado = FuncaoAtivacao.degrau(this.potencial);
				break;
			case FuncaoAtivacao.LIN:
				resultado = FuncaoAtivacao.linear(this.potencial);
				break;
			case FuncaoAtivacao.SIG:
				resultado = FuncaoAtivacao.sigmoide(this.potencial);
				break;
			case FuncaoAtivacao.TAN:
				resultado = FuncaoAtivacao.tangenteHiperbolica(this.potencial);
				break;
			case FuncaoAtivacao.SIG_HEITOR:
				resultado = FuncaoAtivacao.sigmoideHeitor(this.potencial);
				break;
		}
		return resultado;
	}

	public void incrementoPotencial(double potencial){
		this.potencial += potencial;
	}

	public void print(){
		System.out.println("\t\tFuncaoAtivacao: " + funcaoAtivacao + ".");
	}

	public int getFuncaoAtivacao(){
		return this.funcaoAtivacao;
	}

	public void setFuncaoAtivacao(int funcaoAtivacao){
		this.funcaoAtivacao = funcaoAtivacao;
	}

	public double getPotencial(){
		return this.potencial;
	}

	public void setPotencial(double potencial){
		this.potencial = potencial;
	}
}
