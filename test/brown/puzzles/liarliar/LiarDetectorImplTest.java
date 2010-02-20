package brown.puzzles.liarliar;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import brown.puzzles.liarliar.LiarDetector.Response;

/**
 * @date Feb 18, 2010
 */
public class LiarDetectorImplTest {

	private LiarDetector detector;

	@Before
	public void setUp() {
		detector = new LiarDetectorImpl();
	}
	@Test
	public void example() {
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


		List<Accuser> group = Arrays.asList(stephen, tommaso, galileo, issac, george);
		Response resp = detector.detect(group);
		assertEquals(3, resp.getLarger());
		assertEquals(2, resp.getSmaller());
	}

	@Test
	public void twoRoots() {
		Accuser p1 = new Accuser("p1");
		Accuser p2 = new Accuser("p2");
		Accuser p3 = new Accuser("p3");

		p1.accuse(p3);
		p2.accuse(p3);

		List<Accuser> group = Arrays.asList(p1, p2, p3);
		Response resp = detector.detect(group);
		assertEquals(2, resp.getLarger());
		assertEquals(1, resp.getSmaller());
	}

	@Test
	public void disconnectedGroups() {
		Accuser p1 = new Accuser("p1");
		Accuser p2 = new Accuser("p2");
		Accuser p3 = new Accuser("p3");

		p1.accuse(p3);
		p2.accuse(p3);

		Accuser p4 = new Accuser("p4");
		Accuser p5 = new Accuser("p5");
		Accuser p6 = new Accuser("p6");

		p4.accuse(p6);
		p5.accuse(p6);

		List<Accuser> group = Arrays.asList(p1, p2, p3, p4, p5, p6);
		Response resp = detector.detect(group);
		assertEquals(4, resp.getLarger());
		assertEquals(2, resp.getSmaller());
	}
}
