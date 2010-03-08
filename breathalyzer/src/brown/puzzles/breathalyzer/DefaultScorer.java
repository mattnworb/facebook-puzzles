package brown.puzzles.breathalyzer;

import java.util.List;
import brown.puzzles.Logger;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class DefaultScorer implements Scorer {

	private final List<String> corpus;

	public DefaultScorer(List<String> corpus) {
		this.corpus = corpus;
	}

	public int score(List<String> words) {

		int score = 0;
		for (String word : words) {

			int min = Integer.MAX_VALUE;
			String minWord = null;
			for (String c : corpus) {
				int l = Levenshtein.score(word, c);
				if (l < min) {
					min = l;
					minWord = c;
				}
			}

			score += min;
			Logger.log(word, minWord, min);

		}
		return score;
	}

}
