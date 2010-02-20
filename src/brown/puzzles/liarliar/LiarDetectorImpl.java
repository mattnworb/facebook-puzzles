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
		Map<Accuser, Integer> countMap = countEntries(group);

		log("count table: " + countMap);


		// find anyone with no accusations against him/her
		List<Accuser> roots = findRoots(countMap);
		log("roots: " + roots);

		Set<Accuser> honest = new HashSet<Accuser>();
		Set<Accuser> liars = new HashSet<Accuser>();

		List<Accuser> honestQueue = new ArrayList<Accuser>();
		List<Accuser> liarsQueue = new ArrayList<Accuser>();

		honest.addAll(roots);
		honestQueue.addAll(roots);
		log("added [" + roots.size() + "] roots to honestQueue");

		while (!group.isEmpty()) {

			log("loop iteration, group size [" + group.size() + "]");

			if (honestQueue.isEmpty() && liarsQueue.isEmpty()) {
				log("stuck!");
				log("original: " + accusers);
				log("roots: " + roots);
				log("honest: " + honest);
				log("liars: " + liars);
				log("group: " + group);

				return null;
			}

			// examine honest queue, then liar queue
			iterate(honestQueue, honest, liarsQueue, liars, group);
			iterate(liarsQueue, liars, honestQueue, honest, group);
		}

		return new Response(honest.size(), liars.size());
	}

	private List<Accuser> findRoots(Map<Accuser, Integer> countMap) {
		// this scans the map - bad
		List<Accuser> roots = new ArrayList<Accuser>();
		for (Map.Entry<Accuser, Integer> entry : countMap.entrySet()) {
			if (entry.getValue().intValue() == 0) {
				roots.add(entry.getKey());
			}
		}
		return roots;
	}

	private Map<Accuser, Integer> countEntries(Set<Accuser> group) {
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
				countMap.put(accused, count + 1);
			}
		}
		return countMap;
	}

	// these parameter names suck
	private void iterate(List<Accuser> currentQueue, Set<Accuser> thisSet,
			List<Accuser> otherQueue, Set<Accuser> otherSet, Set<Accuser> group) {
		while (!currentQueue.isEmpty()) {
			Accuser next = currentQueue.remove(0);

			otherQueue.addAll(next.getAccused());
			for (Accuser a : next.getAccused()) {
				if (!thisSet.contains(a)) {
					otherSet.add(a);
				}
			}
			group.remove(next);
		}
	}

	private static void log(String message) {
		// System.out.println(message);
	}
}
