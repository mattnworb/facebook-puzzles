package brown.puzzles.liarliar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matt Brown
 * @date Feb 21, 2010
 */
public class AccuserInputParserTest {

	AccuserInputParser parser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parser = new AccuserInputParser();
	}

	@Test
	public void sampleFile() throws Exception {
		Collection<Accuser> group = parser.parseFile(new File("samples/sample.txt"));

		assertEquals(5, group.size());
		Map<String, Accuser> map = new HashMap<String, Accuser>();
		for (Accuser a : group) {
			map.put(a.getName(), a);
		}

		/**
		 * stephen.accuse(tommaso); tommaso.accuse(galileo);
		 * galileo.accuse(tommaso); issac.accuse(tommaso);
		 * george.accuse(stephen); george.accuse(issac);
		 */

		Accuser stephen = map.get("Stephen");
		assertNotNull(stephen);
		Accuser george = map.get("George");
		assertNotNull(george);

		assertEquals(2, george.getAccused().size());

		Accuser tomasso = map.get("Tommaso");
		assertSame(tomasso, stephen.getAccused().get(0));
		assertTrue(stephen.getAccused().get(0).getAccusedBy().contains(stephen));

	}

}
