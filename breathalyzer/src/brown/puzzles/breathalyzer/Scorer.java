package brown.puzzles.breathalyzer;

import java.util.List;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public interface Scorer {

	int score(List<String> words);
}
