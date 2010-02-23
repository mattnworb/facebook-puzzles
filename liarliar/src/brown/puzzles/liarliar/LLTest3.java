package brown.puzzles.liarliar;

/**
 * @author David Eisenstat
 * @see <a href=
 *      "http://www.facebook.com/topic.php?uid=15325934266&topic=9670&start=0&hash=bc5a7e1758b4cdec18e4fc7f28e176b2#topic_top">Facebook
 *      puzzle forum</a>
 */
public class LLTest3 {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]) & ~1;
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedWriter(
			new java.io.OutputStreamWriter(System.out)));
		out.printf("%d\n", n);
		for (int i = 0; i < n; ++i) {
			out.printf("%d\t%d\n%d\n", i, 1, (i + 1) % n);
		}
		out.close();
	}
}