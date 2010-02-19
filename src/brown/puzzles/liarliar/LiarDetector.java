package brown.puzzles.liarliar;

import java.util.Collection;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public interface LiarDetector {

	Response detect(Collection<Accuser> accusers);

	public static class Response {

		private int larger;

		private int smaller;

		public Response(int group1, int group2) {
			this.larger = Math.max(group1, group2);
			this.smaller = Math.min(group1, group2);
		}

		public int getLarger() {
			return larger;
		}

		public int getSmaller() {
			return smaller;
		}
	}
}
