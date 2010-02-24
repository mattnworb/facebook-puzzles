import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hoppity {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Usage: Hoppity <filename>");
			System.exit(-1);
		}

		try {
			int num = readFileForNum(args[0]);

			for (int i = 1; i <= num; i++) {
				if (i % 15 == 0) {
					System.out.println("Hop");
				}
				else if (i % 3 == 0) {
					System.out.println("Hoppity");
				}
				else if (i % 5 == 0) {
					System.out.println("Hophop");
				}
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	private static int readFileForNum(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));

		String line = reader.readLine();

		return Integer.parseInt(line.trim());
	}
}
