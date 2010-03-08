package brown.puzzles.breathalyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: Main <file to score>");
			System.exit(-1);
		}

		try{
			List<String> words = readWords(args);

			// int result = (new DefaultScorer()).score(readCorpus(), words);
			int result = (new LengthAwareScorer()).score(readOrderedCorpus(), words);

			System.out.print(result + "\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> readWords(String[] args) throws IOException {
		BreathalyzerInputParser parser = new BreathalyzerInputParser();
		List<String> words = parser.parseFile(new File(args[0]));
		return words;
	}

	public static List<String> readCorpus() throws IOException {
		CorpusInputParser corpusParser = new CorpusInputParser();
		File corpusFile = new File("/var/tmp/twl06.txt");
		if (!corpusFile.exists()) {
			corpusFile = new File("twl06.txt");
		}
		return corpusParser.parseFile(corpusFile);
	}

	public static Map<Integer, List<String>> readOrderedCorpus() throws IOException {
		OrderedCorpusInputParser corpusParser = new OrderedCorpusInputParser();
		File corpusFile = new File("/var/tmp/twl06.txt");
		if (!corpusFile.exists()) {
			corpusFile = new File("twl06.txt");
		}
		return corpusParser.parseFile(corpusFile);
	}
}
