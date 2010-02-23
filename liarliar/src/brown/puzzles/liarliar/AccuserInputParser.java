package brown.puzzles.liarliar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import brown.puzzles.InputParser;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 */
public class AccuserInputParser implements InputParser<Collection<Accuser>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see brown.puzzles.InputParser#parseFile(java.io.File)
	 */
	public Collection<Accuser> parseFile(File file) throws IOException {

		Map<String, Accuser> map = new HashMap<String, Accuser>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
			new FileInputStream(file)));

		final int numLines = Integer.parseInt(reader.readLine().trim());

		for (int i = 0; i < numLines; i++) {
			String[] sp = reader.readLine().split("\\s+");

			final String name = sp[0].trim();
			Accuser a = getAccuser(map, name);

			final int accusations = Integer.parseInt(sp[1].trim());

			for (int j = 0; j < accusations; j++) {
				Accuser b = getAccuser(map, reader.readLine().trim());
				a.addAccusation(b);
			}
		}

		return map.values();
	}

	private Accuser getAccuser(Map<String, Accuser> map, final String name) {
		if (!map.containsKey(name)) {
			map.put(name, new Accuser(name));
		}
		return map.get(name);
	}
}
