package brown.puzzles.liarliar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class Accuser implements Comparable<Accuser> {

	private String name;

	private List<Accuser> accused;

	private List<Accuser> accusedBy;

	public Accuser(String name) {
		this.name = name;
		this.accused = new ArrayList<Accuser>();
		this.accusedBy = new ArrayList<Accuser>();
	}

	public String getName() {
		return name;
	}

	/**
	 * Add an accusation from this Node to a. Also adds this instance to a's
	 * accusedBy list
	 * 
	 * @param a
	 */
	public void addAccusation(Accuser a) {
		accused.add(a);
		a.accusedBy.add(this);
	}

	public List<Accuser> getAccused() {
		return Collections.unmodifiableList(accused);
	}

	public List<Accuser> getAccusedBy() {
		return Collections.unmodifiableList(accusedBy);
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
	public int compareTo(Accuser o) {
		return this.name.compareTo(o.name);
	}
}
