package brown.puzzles.liarliar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class Accuser {

	private String name;

	private List<Accuser> accused;

	public Accuser(String name) {
		this.name = name;
		this.accused = new ArrayList<Accuser>();
	}

	public String getName() {
		return name;
	}

	public void accuse(Accuser a) {
		accused.add(a);
	}

	public List<Accuser> getAccused() {
		return Collections.unmodifiableList(accused);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Accuser other = (Accuser) obj;
		if (name == null) {
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!accused.isEmpty()) {
			for (Accuser obj : accused) {
				sb.append(obj.name).append(",");
			}
			sb.delete(sb.length() - 1, sb.length());
		}
		sb.append("]");

		return "[name=" + name + ",accused=" + sb.toString() + "]";
	}
}