package brown.puzzles.breathalyzer;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import brown.puzzles.breathalyzer.LengthAwareScorer.DistanceBasedComparator;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class LengthAwareScorerTest {

	@Test
	public void testComparator() {
		final int len = 5;
		DistanceBasedComparator dc = new DistanceBasedComparator(len);

		// [1, 3, 4, 5, 6, 7, 8, 9, 11]
		// should become
		// [5, 4, 6, 3, 7, 8, 1, 9, 11]
		Set<Integer> originalNums = new TreeSet(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9, 11));

		Set<Integer> orderedNums = new TreeSet(dc);
		orderedNums.addAll(originalNums);

		List<Integer> l = new ArrayList<Integer>(orderedNums);
		assertEquals(Arrays.asList(5, 4, 6, 3, 7, 8, 1, 9, 11), l);

	}
}
