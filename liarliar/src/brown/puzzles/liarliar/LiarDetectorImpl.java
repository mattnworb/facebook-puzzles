package brown.puzzles.liarliar;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class LiarDetectorImpl implements LiarDetector {

	private Partition<Accuser> partition1 = new Partition<Accuser>("p1");

	private Partition<Accuser> partition2 = new Partition<Accuser>("p2");

	@Override
	public Response detect(Collection<Accuser> accusers) {

		// group should not be an ArrayList or any datatype where
		// indexing/iteration is costly, since we need to call removeAll() on it
		// often.
		SortedSet<Accuser> group = new TreeSet<Accuser>(accusers);

		// linkedLists for the p1 and p2 queues work poorly because we need to
		// enqueue entire collections of nodes at a time. With a linkedList, we
		// have no performant way to check if an item is already in the list
		// (this involves scanning the linked list)
		SortedSet<Accuser> p1Queue = new TreeSet<Accuser>();
		SortedSet<Accuser> p2Queue = new TreeSet<Accuser>();

		while (!group.isEmpty()) {

			// enqueue the first item in the list.
			// if we are coming back here after iterations of the below loop, it
			// means we have a disconnected graph and we need to start working
			// on another segment of the graph. Partition1 is picked
			// arbitrarily.
			Accuser root = dequeue(group);

			p1Queue.add(root);

			while (!p1Queue.isEmpty() || !p2Queue.isEmpty()) {

				while (!p1Queue.isEmpty()) {
					Accuser p1 = dequeue(p1Queue);

					if (!isLabeled(p1)) {
						addToPartition(partition1, "p1", p1);

						enqueue(p2Queue, p1.getAccused());
						enqueue(p2Queue, p1.getAccusedBy());
					}
				}

				while (!p2Queue.isEmpty()) {
					Accuser p2 = dequeue(p2Queue);

					if (!isLabeled(p2)) {
						addToPartition(partition2, "p2", p2);

						enqueue(p1Queue, p2.getAccused());
						enqueue(p1Queue, p2.getAccusedBy());
					}
				}

			}

			group.removeAll(partition1.getAll());
			group.removeAll(partition2.getAll());
		}

		return new Response(partition1.size(), partition2.size());
	}

	private void addToPartition(Partition<Accuser> partition, String labelName,
			Accuser node) {
		partition.add(node);
		node.addLabel(labelName);
	}

	/**
	 * Adds Collection of nodes to Queue
	 * 
	 * @param queue
	 * @param nodes
	 */
	private void enqueue(SortedSet<Accuser> queue, Collection<Accuser> nodes) {

		// instead of using addAll() to add the full collection, test each node
		// to see if it is contained in the queue first.

		// This is ONLY a good idea when we happen to know that the contains
		// operation of the 'queue' is better than O(N). For TreeSet, it is
		// guaranteed O(log(N)).
		for (Accuser node : nodes) {
			if (!isLabeled(node) && !queue.contains(node)) {
				queue.add(node);
			}
		}
	}

	private Accuser dequeue(SortedSet<Accuser> queue) {
		Accuser first = queue.first();
		queue.remove(first);
		return first;
	}

	private boolean isLabeled(Accuser node) {
		return node.isLabeled();
	}
}
