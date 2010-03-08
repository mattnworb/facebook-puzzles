package brown.puzzles.breathalyzer;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class DefaultScorerTest {

	@Test
	public void specScore() throws Exception {
		// tihs sententcnes iss nout varrry goud
		List<String> words = Arrays.asList("tihs", "sententcnes", "iss", "nout",
			"varrry", "goud");

		DefaultScorer scorer = new DefaultScorer(Main.readCorpus());
		assertEquals(8, scorer.score(words));
	}
}
