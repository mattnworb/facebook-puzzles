package brown.puzzles.breathalyzer;

import java.util.List;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class DefaultScorer implements Scorer<List<String>> {

	public int score(List<String> corpus, List<String> words) {

		int score = 0;
		for (String word : words) {

			int min = Integer.MAX_VALUE;
			for (String c : corpus) {
				// Don't bother to calculate the score if the length of the two
				// words is greater than min. In this case, the possible score
				// will never be less than the min we've already found. For
				// example, if min=2 and c.length=4 and word.length=8, the
				// score must be >= 4, so calculating the actual score would be
				// a waste of time.
				if (min > Math.abs(c.length() - word.length())
						|| min == Integer.MAX_VALUE) {

					int l = Levenshtein.score(word, c);
					if (l < min) {
						min = l;
					}
				}
			}

			score += min;

		}
		return score;
	}

}
