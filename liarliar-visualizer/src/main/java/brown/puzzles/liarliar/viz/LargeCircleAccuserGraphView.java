package brown.puzzles.liarliar.viz;

import java.util.ArrayList;
import java.util.List;
import brown.puzzles.liarliar.Accuser;
import edu.uci.ics.jung.graph.Graph;

/**
 * @author Matt Brown
 * @date Feb 22, 2010
 */
public class LargeCircleAccuserGraphView extends AccuserGraphView {

	public static void main(String[] args) {
		List<Accuser> list = new ArrayList<Accuser>();

		final Accuser start = new Accuser("start");
		final Accuser second = new Accuser("second");
		start.addAccusation(second);

		list.add(start);
		list.add(second);

		Accuser current = second;

		for (int i = 0; i < 100; i++) {
			Accuser a = new Accuser(String.valueOf(i));
			list.add(a);
			current.addAccusation(a);
			current = a;
		}
		current.addAccusation(second);

		Graph<String, String> graph = AccuserToGraph.toGraph(list);
		System.out.println("generated graph: " + graph);
		AccuserGraphView view = new LargeCircleAccuserGraphView();
		view.drawGraph(graph, 700, 700, "large circle");
	}
}
