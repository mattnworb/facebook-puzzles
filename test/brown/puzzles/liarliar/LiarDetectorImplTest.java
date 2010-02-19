package brown.puzzles.liarliar;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import brown.puzzles.liarliar.LiarDetector.Response;

/**
 * @date Feb 18, 2010
 */
public class LiarDetectorImplTest {

	@Test
	public void exampleTest() {
		Accuser stephen = new Accuser("Stephen");
		Accuser tommaso = new Accuser("Tommaso");
		Accuser galileo = new Accuser("Galileo");
		Accuser issac = new Accuser("Issac");
		Accuser george = new Accuser("George");

		stephen.accuse(tommaso);
		tommaso.accuse(galileo);
		galileo.accuse(tommaso);
		issac.accuse(tommaso);
		george.accuse(stephen);
		george.accuse(issac);

		LiarDetector detector = new LiarDetectorImpl();
		List<Accuser> group = Arrays.asList(stephen, tommaso, galileo, issac, george);
		Response resp = detector.detect(group);
		assertEquals(3, resp.getLarger());
		assertEquals(2, resp.getSmaller());
	}
}
