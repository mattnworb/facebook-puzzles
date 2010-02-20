package brown.puzzles.liarliar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class LiarDetectorImpl implements LiarDetector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see brown.puzzles.liarliar.LiarDetector#detect(java.util.Collection)
	 */
	public Response detect(Collection<Accuser> accusers) {

		Set<Accuser> group = new HashSet<Accuser>(accusers);

		// count number of accusations against each member
		Map<Accuser, Integer> countMap = new HashMap<Accuser, Integer>(group.size());

		// TODO avoid nested loop
		for (Accuser accuser : group) {
			if (!countMap.containsKey(accuser)) {
				countMap.put(accuser, 0);
			}

			for (Accuser accused : accuser.getAccused()) {
				int count = 0;
				if (countMap.containsKey(accused)) {
					count = countMap.get(accused).intValue();
				}
				log(accused + " accused " + count + " times");
				countMap.put(accused, count + 1);
			}
		}

		log("count table: " + countMap);

		Set<Accuser> honest = new HashSet<Accuser>();
		Set<Accuser> liars = new HashSet<Accuser>();

		// find anyone with no accusations against him/her
		// this scans the map - bad
		List<Accuser> roots = new ArrayList<Accuser>();
		for (Map.Entry<Accuser, Integer> entry : countMap.entrySet()) {
			if (entry.getValue().intValue() == 0) {
				roots.add(entry.getKey());
			}
		}

		List<Accuser> honestQueue = new ArrayList<Accuser>();
		List<Accuser> liarsQueue = new ArrayList<Accuser>();

		honest.addAll(roots);
		honestQueue.addAll(roots);
		log("added [" + roots.size() + "] roots to honestQueue");

		while (!group.isEmpty()) {

			log("loop iteration, group size [" + group.size() + "]");

			// examine honest queue, then liar queue
			while (!honestQueue.isEmpty()) {
				Accuser next = honestQueue.remove(0);
				log("popped " + next + " from honestQueue");

				liarsQueue.addAll(next.getAccused());
				liars.addAll(next.getAccused());
				group.remove(next);
			}

			while (!liarsQueue.isEmpty()) {
				Accuser next = liarsQueue.remove(0);
				log("popped " + next + " from liarsQueue");

				honestQueue.addAll(next.getAccused());
				honest.addAll(next.getAccused());
				group.remove(next);
			}
		}

		return new Response(honest.size(), liars.size());
	}

	private static void log(String message) {
		System.out.println(message);
	}
}
