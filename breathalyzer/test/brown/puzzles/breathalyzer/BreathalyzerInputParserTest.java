package brown.puzzles.breathalyzer;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;
import org.junit.Test;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class BreathalyzerInputParserTest {

	@Test
	public void specTest() throws Exception {
		BreathalyzerInputParser parser = new BreathalyzerInputParser();
		List<String> words = parser.parseFile(new File("testcases/spec.txt"));

		// make sure extra whitespace is ignored
		assertEquals(6, words.size());
	}
}
