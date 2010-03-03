package brown.puzzles.breathalyzer.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import brown.puzzles.breathalyzer.Levenshtein;

/**
 * @author Matt Brown
 * @date Mar 2, 2010
 */
public class LevenshteinBenchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			System.out
				.println("Usage: LevenshteinBenchmark <num words> <min length> <max length>");
			System.exit(-1);
		}

		// generate N words of random length in the range A, B
		int count = Integer.parseInt(args[0]);
		int min = Integer.parseInt(args[1]);
		int max = Integer.parseInt(args[2]);

		List<String> corpus = null;
		try {
			corpus = readCorpus("twl06.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		List<String> words = generateWords(count, min, max);

		// start the timer
		long start = System.nanoTime();

		for (String word : words) {
			for (String c : corpus) {
				Levenshtein.score(word, c);
			}
		}

		long ellapsed = System.nanoTime() - start;

		// 1000 nanoseconds in a microsecond, 1000 microseconds in a millisecond

		int calculations = words.size() * corpus.size();
		System.out.printf("Performed %d calculations\n", calculations);

		double msTime = (double) ellapsed / 1000000;
		System.out.printf("Time took %.5f ms\n", msTime);
		System.out.printf("%.5f ns per calculation\n", (double) ellapsed / calculations);
		
	}

	private static List<String> generateWords(int count, int min, int max) {
		List<String> words = new ArrayList<String>(count);

		Random rand = new Random();
		for (int i = 0; i < count; i++) {

			int lenOfWord = min + rand.nextInt(max - min + 1);
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < lenOfWord; j++) {
				// 65-90, 97-122
				int c = 65 + rand.nextInt(52);

				if (c > 90) {
					c += 6;
				}
				sb.append(Character.toString((char) c));
			}

			words.add(sb.toString());
		}
		return words;
	}

	private static List<String> readCorpus(String path) throws IOException {

		List<String> list = new ArrayList<String>();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			String line = null;

			while ((line = reader.readLine()) != null) {
				list.add(line.trim());
			}
		}
		finally {
			if (reader != null) reader.close();
		}
		return list;
	}

}
