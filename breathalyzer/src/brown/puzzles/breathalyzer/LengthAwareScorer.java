package brown.puzzles.breathalyzer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class LengthAwareScorer implements Scorer<Map<Integer, List<String>>> {

	public int score(Map<Integer, List<String>> corpusMap, List<String> words) {

		int score = 0;
		for (String word : words) {

			int min = Integer.MAX_VALUE;

			// start with the words in the corpusMap with the same length, then
			// move on to +/- 1 length off, etc.
			final int len = word.length();
			SortedSet<Integer> keys = new TreeSet<Integer>(new DistanceBasedComparator(len));
			keys.addAll(corpusMap.keySet());

			for (Integer key : keys) {

				for (String c : corpusMap.get(key)) {

					// if the min score found is already less than the
					// difference between word length and the key we are at,
					// then we have no hope of finding a shorter score, so just
					// stop looking.
					if (min < Math.abs(len - key.intValue())) {
						break;
					}

					
					if (min > Math.abs(c.length() - word.length())
							|| min == Integer.MAX_VALUE) {

						int l = Levenshtein.score(word, c);
						if (l < min) {
							min = l;
						}
					}
				}
			}
			score += min;

		}
		return score;
	}

	/**Comparator which orders numbers based on distance from a given integer.
	 * @author Matt Brown
	 * @date Mar 7, 2010
	 */
	public static final class DistanceBasedComparator implements Comparator<Integer> {
	
		private final int len;
	
		/**
		 * @param len
		 *            Number to based distance off of
		 */
		public DistanceBasedComparator(int len) {
			this.len = len;
		}
	
		// sort based on proximity to this word's length
		public int compare(Integer o1, Integer o2) {
			int a = Math.abs(o1.intValue() - len);
			int b = Math.abs(o2.intValue() - len);
			if (a < b) {
				return -1;
			}
			else if (a > b) {
				return 1;
			}
			else {
				// two distances are equal, sort based on the numbers themselves
				return o1.compareTo(o2);
			}
		}
	}

}
