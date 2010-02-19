package brown.puzzles.liarliar;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: Main <filename>");
			System.exit(-1);
		}

		String filename = args[0];
		try {
			AccuserInputParser parser = new AccuserInputParser();
			LiarDetector detector = new LiarDetectorImpl();

			Collection<Accuser> col = parser.parseFile(new File(filename));
			LiarDetector.Response response = detector.detect(col);

			System.out.println(response.getLarger() + " " + response.getSmaller());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
