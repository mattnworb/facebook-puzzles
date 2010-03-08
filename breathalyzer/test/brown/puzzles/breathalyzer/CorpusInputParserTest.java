package brown.puzzles.breathalyzer;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;
import org.junit.Test;



/**
 * @author Matt Brown
 * @date Mar 7, 2010
 *
 */
public class CorpusInputParserTest {

	@Test
	public void numWords() throws Exception {
		CorpusInputParser parser = new CorpusInputParser();
		List<String> words = parser.parseFile(new File("twl06.txt"));

		System.out.println(words.get(0));
		System.out.println(words.get(words.size() - 1));
		assertEquals(178691, words.size());
	}
}
