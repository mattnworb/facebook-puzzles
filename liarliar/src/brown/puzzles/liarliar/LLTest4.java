package brown.puzzles.liarliar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author David Eisenstat
 * @see <a href=
 *      "http://www.facebook.com/topic.php?uid=15325934266&topic=9670&start=0&hash=bc5a7e1758b4cdec18e4fc7f28e176b2#topic_top">Facebook
 *      puzzle forum</a>
 */
class LLTest4 {

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(args[0]);
		Random r = new Random(Long.parseLong(args[1]));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		List<Member> members = new ArrayList<Member>(n);
		for (int i = 0; i < n; ++i) {
			members.add(new Member(r));
			if (i < 1) continue;
			int j = r.nextInt(i);
			if (r.nextBoolean()) {
				members.get(i).accuse(members.get(j));
			}
			else {
				members.get(j).accuse(members.get(i));
			}
		}
		Collections.shuffle(members, r);
		out.printf("%d\n", n);
		for (Member m : members) {
			m.print(out, r);
		}
		out.close();
	}
}

class Member {

	private final String name;

	private final List<Member> accused;

	Member(Random r) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; ++i) {
			sb.append((char) ('a' + r.nextInt('z' - 'a' + 1)));
		}
		name = sb.toString();
		accused = new ArrayList<Member>();
	}

	void accuse(Member m) {
		accused.add(m);
	}

	void print(PrintWriter out, Random r) {
		Collections.shuffle(accused, r);
		out.printf("%s\t%d\n", name, accused.size());
		for (Member m : accused) {
			out.printf("%s\n", m.name);
		}
	}
}