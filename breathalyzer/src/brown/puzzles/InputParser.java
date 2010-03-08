package brown.puzzles;


import java.io.File;
import java.io.IOException;

/**
 * @author Matt Brown
 * @date Feb 18, 2010
 *
 */
public interface InputParser<T> {

	T parseFile(File file) throws IOException;
}
