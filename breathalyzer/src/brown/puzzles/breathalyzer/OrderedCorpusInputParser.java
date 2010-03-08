package brown.puzzles.breathalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import brown.puzzles.InputParser;


/**
 * @author Matt Brown
 * @date Mar 7, 2010
 *
 */
public class OrderedCorpusInputParser implements InputParser<Map<Integer, List<String>>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see brown.puzzles.InputParser#parseFile(java.io.File)
	 */
	public Map<Integer, List<String>> parseFile(File file) throws IOException {

		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String line = null;

			while ((line = br.readLine()) != null) {
				Integer len = Integer.valueOf(line.length());
				if (!map.containsKey(len)) {
					map.put(len, new ArrayList<String>());
				}
				map.get(len).add(line);
			}
		}
		finally {
			if (br != null) br.close();
		}

		return map;
	}
}
