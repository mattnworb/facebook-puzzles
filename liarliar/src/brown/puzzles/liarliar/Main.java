package brown.puzzles.liarliar;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: Main <filename>");
			System.exit(-1);
		}

		String filename = args[0];

		Main main = new Main();
		try {
			LiarDetector.Response response = main.execute(filename);
			if (response == null) {
				System.out.println("oops");
			}
			else {
				System.out.println(response.getLarger() + " " + response.getSmaller());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LiarDetector.Response execute(String filename) throws IOException {
		AccuserInputParser parser = new AccuserInputParser();
		LiarDetector detector = new LiarDetectorImpl();

		Collection<Accuser> col = parser.parseFile(new File(filename));
		LiarDetector.Response response = detector.detect(col);
		return response;
	}

}
