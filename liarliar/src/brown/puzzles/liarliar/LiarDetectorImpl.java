package brown.puzzles.liarliar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class LiarDetectorImpl implements LiarDetector {

	private Partition<Accuser> partition1 = new Partition<Accuser>("p1");

	private Partition<Accuser> partition2 = new Partition<Accuser>("p2");

	/*
	 * (non-Javadoc)
	 * 
	 * @see brown.puzzles.liarliar.LiarDetector#detect(java.util.Collection)
	 */
	public Response detect(Collection<Accuser> accusers) {

		log("starting with size " + accusers.size());

		List<Accuser> group = new ArrayList<Accuser>(accusers);

		Queue<Accuser> p1Queue = new LinkedList<Accuser>();
		Queue<Accuser> p2Queue = new LinkedList<Accuser>();

		while (!group.isEmpty()) {

			// enqueue the first item in the list.
			// if we are coming back here after iterations of the below loop, it
			// means we have a disconnected graph and we need to start working
			// on another segment of the graph. Partition1 is picked
			// arbitrarily.
			Accuser root = group.remove(0);

			p1Queue.add(root);

			while (!p1Queue.isEmpty() || !p2Queue.isEmpty()) {

				while (!p1Queue.isEmpty()) {
					Accuser p1 = p1Queue.remove();
					log("popped " + p1 + " from p1Queue");

					if (!isLabeled(p1)) {
						addToPartition(partition1, p1);

						enqueue(p2Queue, p1.getAccused());
						enqueue(p2Queue, p1.getAccusedBy());
						group.removeAll(p1.getAccused());
						group.removeAll(p1.getAccusedBy());
					}
				}

				while (!p2Queue.isEmpty()) {
					Accuser p2 = p2Queue.remove();
					log("popped " + p2 + " from p2Queue");

					if (!isLabeled(p2)) {
						addToPartition(partition2, p2);

						enqueue(p1Queue, p2.getAccused());
						enqueue(p1Queue, p2.getAccusedBy());
						group.removeAll(p2.getAccused());
						group.removeAll(p2.getAccusedBy());
					}
				}

			}
		}

		log("exiting - p1 size [" + partition1.size() + "] p2 size [" + partition2.size() + "]");

		return new Response(partition1.size(), partition2.size());
	}

	private void addToPartition(Partition<Accuser> partition, Accuser node) {
		log("adding " + node + " to " + partition);
		partition.add(node);
	}

	/**
	 * Adds Collection of nodes to Queue
	 * 
	 * @param queue
	 * @param nodes
	 */
	private void enqueue(Queue<Accuser> queue, Collection<Accuser> nodes) {
		queue.addAll(nodes);
	}

	private boolean isLabeled(Accuser node) {
		return partition1.contains(node) || partition2.contains(node);
	}

	private static void log(String message) {
		if (System.getProperty("liarliar.log") == null) System.out.println(message);
	}
}
