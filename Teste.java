


public class Teste{

	public static void main(String args[]){
		double pesos[][] = {{1, 2, 3},{5, 6, 7},{8, 9, 4}};
		double p[][] = {{2, 3}, {4, 5}};
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(pesos[i][j]);
			}
		}

		System.out.println("\n");
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(p[i][j]);
			}
		}

		pesos = p;

		System.out.println("\n");

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(pesos[i][j]);
			}
		}

		System.out.println("\n");
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(p[i][j]);
			}
		}
	}
}