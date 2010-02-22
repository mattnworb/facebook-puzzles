package brown.puzzles.liarliar.viz;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import brown.puzzles.liarliar.Accuser;
import edu.uci.ics.jung.graph.Graph;

/**
 * @author Matt Brown
 * @date Feb 21, 2010
 */
public class AccuserToGraphTest {

	@Test
	public void simpleGraph() {
		Accuser a = new Accuser("a");
		Accuser b = new Accuser("b");
		Accuser c = new Accuser("c");

		a.addAccusation(b);
		b.addAccusation(c);
		c.addAccusation(b);

		Graph<String, String> graph = AccuserToGraph.toGraph(Arrays.asList(a, b, c));
		assertEquals(3, graph.getVertexCount());
		assertEquals(3, graph.getEdgeCount());
	}
}
