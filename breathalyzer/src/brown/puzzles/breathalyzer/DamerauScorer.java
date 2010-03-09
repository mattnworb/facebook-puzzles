package brown.puzzles.breathalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Use the <a href="http://pcplus.techradar.com/node/3062">Damerau method</a> to
 * generate lists of candidate words that have N differences from the input word
 * for increasing values of N, checking if the corpus contains any of the
 * candidate words.
 * 
 * @author Matt Brown
 * @date Mar 8, 2010
 */
public class DamerauScorer implements Scorer<List<String>> {

	public int score(List<String> corpus, List<String> words) {

		Set<String> corpusSet = new HashSet<String>(corpus.size());
		for (String s : corpus) {
			corpusSet.add(s.toLowerCase());
		}

		int score = 0;
		for (String word : words) {

			if (corpusSet.contains(word)) {
				break;
			}

		}
		return score;
	}

	private List<String> generateWordList(final String word, int differences) {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < differences; i++) {

			// omit
			// add
			// replace
		}
		return list;
	}
}
