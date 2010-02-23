package brown.puzzles.liarliar;

/**
 * @author David Eisenstat
 * @see <a href=
 *      "http://www.facebook.com/topic.php?uid=15325934266&topic=9670&start=0&hash=bc5a7e1758b4cdec18e4fc7f28e176b2#topic_top">Facebook
 *      puzzle forum</a>
 */
class LLTest5 {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		n -= n % 4;
		System.out.printf("%d\n", n + 1);
		for (int i = 0; i < n; i += 2) {
			System.out.printf("%d\t%d\n%d\n", i + 1, 1, i);
		}
		for (int i = 0; i < n; i += 4) {
			System.out.printf("%d\t%d\n%d\n", i, 1, (i + 4) % n);
		}
		for (int i = n - 2; i > -2; i -= 4) {
			System.out.printf("%d\t%d\n%d\n", i, 1, (i + 4) % n);
		}
		System.out.printf("%d\t%d\n%d\n%d\n", n, 2, 0, 2);
	}
}