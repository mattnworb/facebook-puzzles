package brown.puzzles.liarliar.viz;

import java.util.Collection;
import brown.puzzles.liarliar.Accuser;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * @author Matt Brown
 * @date Feb 21, 2010
 */
public class AccuserToGraph {

	public static Graph<String, String> toGraph(Collection<Accuser> group) {
		Graph<String, String> g = new SparseMultigraph<String, String>();

		for (Accuser a : group) {
			g.addVertex(a.getName());
		}
		for (Accuser a : group) {
			final String aName = a.getName();
			for (Accuser b : a.getAccused()) {
				final String bName = b.getName();
				g.addEdge(aName + " accuses " + bName, aName, bName, EdgeType.DIRECTED);
			}
		}

		return g;

	}
}
