package Brainiac;

public abstract class FuncaoAtivacao{
	public static final int DEG = 0;
	public static final int LIN = 1;
	public static final int SIG = 2;
	public static final int TAN = 3;

	public static double degrau(double potencial){
		return (potencial > 0 ? 1 : 0);
	}

	public static double derivadaDegrau(double potencial){
		return (potencial == 0 ? Double.NaN: 0);
	}

	public static double linear(double potencial){
		return potencial;
	}

	public static double derivadaLinear(double potencial){
		return 1;
	}

	public static double sigmoide(double potencial){
		return (1 / (1 + Math.exp(-potencial)));
	}

	public static double derivadaSigmoide(double potencial){
		return sigmoide(potencial)*(1 - sigmoide(potencial));
	}

	public static double tangenteHiperbolica(double potencial){
		return ((1 - Math.exp(-potencial)) / (1 + Math.exp(-potencial)));
	}

	public static double derivadaTangenteHiperbolica(double potencial){
		return (1 - Math.pow(tangenteHiperbolica(potencial), 2));
	}
}
