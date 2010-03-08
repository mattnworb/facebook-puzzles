package brown.puzzles.breathalyzer;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class OrderedCorpusInputParserTest {

	@Test
	public void numWords() throws Exception {
		OrderedCorpusInputParser parser = new OrderedCorpusInputParser();
		Map<Integer, List<String>> words = parser.parseFile(new File("twl06.txt"));

		int sum = 0;
		for (Integer key : words.keySet()) {
			sum += words.get(key).size();
		}
		assertEquals(178691, sum);
	}
}
