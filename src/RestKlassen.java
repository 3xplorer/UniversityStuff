
public class RestKlassen {

	static final int MODULO = 10;
	static final int WIDTH = 20;
	public static void main(String[] args) {

		addCongruencClasses();
		multCongruenceClasses();
	}

	private static void multCongruenceClasses() {

		int[][] mult = new int[WIDTH][WIDTH];

		System.out.println("\n\nMultiplikation auf Z/" + MODULO +"Z\n");
		for (int i = 0; i < mult.length; i++) {

			for (int j = 0; j < mult[i].length; j++) {

				mult[i][j] = (i * j) % MODULO;
				if (((i * j) % 15) < 10) {

					System.out.print(" " + mult[i][j] + " ");
				} else {

					System.out.print(mult[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}

	private static void addCongruencClasses() {
		int[][] add = new int[WIDTH][WIDTH];

		System.out.println("Addition auf Z/" + MODULO +"Z\n");
		for (int i = 0; i < add.length; i++) {

			for (int j = 0; j < add[i].length; j++) {

				add[i][j] = (i + j) % MODULO;
				if (((i + j) % 15) < 10) {
					
					if(i == j) {
						System.out.print(" '" + add[i][j] + "' ");
					} else {
						
						System.out.print(" " + add[i][j] + " ");
					}
				} else {
					
					if(i == j) {
						
						System.out.print("'" + add[i][j] + "' ");
					} else {
						
						System.out.print(add[i][j] + " ");
					}
				}
			}
			System.out.println();
		}
	}
}
