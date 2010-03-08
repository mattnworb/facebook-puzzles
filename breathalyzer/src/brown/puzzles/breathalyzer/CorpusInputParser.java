package brown.puzzles.breathalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import brown.puzzles.InputParser;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class CorpusInputParser implements InputParser<List<String>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see brown.puzzles.InputParser#parseFile(java.io.File)
	 */
	public List<String> parseFile(File file) throws IOException {
		List<String> list = new ArrayList<String>();

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String line = null;

			while ((line = br.readLine()) != null) {
				list.add(line.toLowerCase());
			}
		}
		finally {
			if (br != null) br.close();
		}
		return list;
	}

}
