package brown.puzzles.liarliar.viz;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import javax.swing.JFrame;
import brown.puzzles.liarliar.Accuser;
import brown.puzzles.liarliar.AccuserInputParser;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author Matt Brown
 * @date Feb 21, 2010
 */
public class AccuserGraphView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Usage: AccuserGraphView <filename> [<width> <height>]");
			System.exit(-1);
		}

		String filename = args[0];
		File file = new File(filename);

		Graph<String, String> graph = null;
		try {
			graph = readFile(file);
		}
		catch (FileNotFoundException e) {
			System.err.println("Cannot find file " + file.getAbsolutePath());
			System.exit(-1);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		int width = 300;
		int height = 300;

		if (args.length >= 3) {
			width = Integer.parseInt(args[1]);
			height = Integer.parseInt(args[2]);
		}

		drawGraph(graph, height, width, file.getName());
	}

	private static Graph<String, String> readFile(File file) throws IOException {
		AccuserInputParser parser = new AccuserInputParser();
		Collection<Accuser> group = parser.parseFile(file);

		Graph<String, String> graph = AccuserToGraph.toGraph(group);
		return graph;
	}

	private static void drawGraph(Graph<String, String> graph, int width, int height,
			String name) {
		Layout<String, String> layout = new CircleLayout<String, String>(graph);
		layout.setSize(new Dimension(width, height));

		VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(
			layout);
		int offset = 50;
		vv.setPreferredSize(new Dimension(width + offset, height + offset));

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		// vv.getRenderContext().setEdgeLabelTransformer(new
		// ToStringLabeller<String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vv.setGraphMouse(gm);

		JFrame frame = new JFrame("liarliar visualization - " + name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}
