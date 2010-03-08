package brown.puzzles;

/**
 * @author Matt Brown
 * @date Mar 7, 2010
 */
public class Logger {

	private static boolean enabled;
	static {
		enabled = Boolean.valueOf(System.getProperty("log.enabled")).booleanValue();
	}

	public static void log(Object... objects) {
		if (enabled) {
			for (Object obj : objects) {
				System.out.print(obj + " ");
	}
			System.out.print("\n");
		}
	}
}
