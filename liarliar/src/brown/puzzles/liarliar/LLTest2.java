package brown.puzzles.liarliar;

/**
 * Usage: java LLTest2 the right answer is always 4095 4095 warning: very slight
 * chance of producing an invalid input
 * 
 * @author David Eisenstat
 * @see <a href=
 *      "http://www.facebook.com/topic.php?uid=15325934266&topic=9670&start=0&hash=bc5a7e1758b4cdec18e4fc7f28e176b2#topic_top">Facebook
 *      puzzle forum</a>
 */
class LLTest2 {

	static final int m = 8191;

	static int pm(int x, int n) {
		if (n == 0)
			return 1;
		else {
			int y = pm((x * x) % m, n / 2);
			return n % 2 == 0 ? y : (y * x) % m;
		}
	}

	public static void main(String[] args) {
		java.util.Random r = new java.util.Random();
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedWriter(
			new java.io.OutputStreamWriter(System.out)));
		out.printf("%d\n", m - 1);
		for (int i = 1; i < m; ++i) {
			int d = 15 + r.nextInt(15);
			out.printf("%d\t%d\n", i, d);
			for (int j = 0; j < d; ++j) {
				out.printf("%d\n", (17 * i * pm(11, r.nextInt(m - 1))) % m);
			}
		}
		out.close();
	}
}
