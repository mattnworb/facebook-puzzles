package brown.puzzles.liarliar;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.junit.Test;
import brown.puzzles.liarliar.LiarDetector.Response;

/**
 * @author Matt Brown
 * @date Feb 20, 2010
 *
 */
public class LLGeneratorTest {

	@Test
	public void lltfile() throws Exception {
		runTest("llt.txt");
	}

	@Test
	public void lltenfile() throws Exception {
		runTest("llten.txt");
	}

	private void runTest(String filename) throws IOException {
		Main main = new Main();
		Response resp = main.execute(filename);
		assertNotNull(resp);
	}
}
