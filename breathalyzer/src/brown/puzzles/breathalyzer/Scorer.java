package brown.puzzles.breathalyzer;

import java.util.List;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public interface Scorer<T> {

	int score(T corpus, List<String> words);
}
