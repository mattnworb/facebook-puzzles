package brown.puzzles.liarliar.viz;

import java.awt.Dimension;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author Matt Brown
 * @date Feb 21, 2010
 */
public class SimpleGraphView {

	private Graph<Integer, String> g;

	public SimpleGraphView() {
		g = new SparseMultigraph<Integer, String>();
		g.addVertex((Integer) 1);
		g.addVertex((Integer) 2);
		g.addVertex((Integer) 3);
		g.addEdge("Edge-A", 1, 2);
		g.addEdge("Edge-B", 2, 3);
		/*
		 * // Note that we can use the same nodes and edges in two different //
		 * graphs. Graph<Integer, String> g2 = new SparseMultigraph<Integer,
		 * String>(); g2.addVertex((Integer) 1); g2.addVertex((Integer) 2);
		 * g2.addVertex((Integer) 3); g2.addEdge("Edge-A", 1, 3);
		 * g2.addEdge("Edge-B", 2, 3, EdgeType.DIRECTED); g2.addEdge("Edge-C",
		 * 3, 2, EdgeType.DIRECTED); g2.addEdge("Edge-P", 2, 3); // A parallel
		 * edge
		 */
	}

	public static void main(String[] args) {
		SimpleGraphView sgv = new SimpleGraphView(); // We create our graph in
		// here
		// The Layout<V, E> is parameterized by the vertex and edge types
		Layout<Integer, String> layout = new CircleLayout<Integer, String>(sgv.g);
		layout.setSize(new Dimension(300, 300)); // sets the initial size of the
		// space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(
			layout);
		vv.setPreferredSize(new Dimension(350, 350)); // Sets the viewing area
		// size

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
