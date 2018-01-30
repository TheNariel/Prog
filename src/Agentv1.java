import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agentv1 implements Agent {

	Stack<String> path;

	public void init(Collection<String> percepts) {
		/*
		 * Possible percepts are: - "(SIZE x y)" denoting the size of the environment,
		 * where x,y are integers - "(HOME x y)" with x,y >= 1 denoting the initial
		 * position of the robot - "( o)" with o in {"NORTH", "SOUTH", "EAST", "WEST"}
		 * denoting the initial orientation of the robot - "(AT o x y)" with o being
		 * "DIRT" or "OBSTACLE" denoting the position of a dirt or an obstacle Moving
		 * north increases the y coordinate and moving east increases the x coordinate
		 * of the robots position. The robot is turned off initially, so don't forget to
		 * turn it on.
		 */
		int x = -5, y = -5, width = -5, height = -5;
		String orientation = "", type = "";
		List<Point> obstacles = new ArrayList<Point>();
		List<Point> dirt = new ArrayList<Point>();
		Pattern perceptNamePattern = Pattern.compile("\\(\\s*([^\\s]+).*");
		for (String percept : percepts) {
			Matcher perceptNameMatcher = perceptNamePattern.matcher(percept);
			if (perceptNameMatcher.matches()) {
				String perceptName = perceptNameMatcher.group(1);
				if (perceptName.equals("HOME")) {
					Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						x = Integer.parseInt(m.group(1));
						y = Integer.parseInt(m.group(2));
					}
				}
				if (perceptName.equals("SIZE")) {
					Matcher m = Pattern.compile("\\(\\s*SIZE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						width = Integer.parseInt(m.group(1));
						height = Integer.parseInt(m.group(2));
					}
				}
				if (perceptName.equals("ORIENTATION")) {
					Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s+(NORTH|SOUTH|EAST|WEST)\\s*\\)")
							.matcher(percept);
					if (m.matches()) {
						orientation = m.group(1);
					}
				}
				if (perceptName.equals("AT")) {
					Matcher m = Pattern.compile("\\(\\s*AT\\s+(DIRT|OBSTACLE)+\\s+([0-9]+)\\s+([0-9]+)\\s*\\)")
							.matcher(percept);
					if (m.matches()) {
						type = m.group(1);
						if (type.equals("DIRT")) {
							dirt.add(new Point(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3))));
						} else {
							obstacles.add(new Point(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3))));
						}
					}
				}
			} else {
				System.err.println("strange percept that does not match pattern: " + percept);
			}

		} // public Environment(int width, int height, Point homeloc, String
			// homeOr,List<Point> obstacles)
		Environment en = new Environment(width, height, new Point(x, y), orientation, obstacles);
		// public EnviroState(List<Point> dirts, List<Point> Agents, String AgentOr,
		// boolean On)
		EnviroState enState = new EnviroState(dirt, new Point(x, y), orientation, false);
		System.out.println("size|home:");
		System.out.println(en.width + ":" + en.height + "|" + en.homeloc.x + ":" + en.homeloc.y + ":" + en.homeOr);
		System.out.print("obstacles: ");
		/*
		 * for (Point p : en.obstacles) { System.out.println(p.x + ":" + p.y); }
		 */

		System.out.println(en.obstacles.size());
		
		System.out.print("dirts: ");
		/*
		 * for (Point p : enState.dirts) { System.out.println(p.x + ":" + p.y); }
		 */
		System.out.println(enState.dirts.size());
		System.out.println("agent initial info:");
		System.out.println(enState.On + "|" + enState.Agent.x + ":" + enState.Agent.y + ":" + enState.AgentOr);
		EnviroState goal = new EnviroState(new ArrayList<Point>(), enState.Agent, enState.AgentOr, false);
		System.out.println("goal: ");
		System.out.println(goal.toString());
<<<<<<< HEAD
		DFS searchDFS = new DFS(enState, goal, en);
                //BFS searchBFS = new BFS(enState, goal, en);
		
		System.out.println("rout:");
		long startTime = System.nanoTime();
		
		path = searchDFS.start();
                //path = searchBFS.start();

		
		long endTime   = System.nanoTime();		
=======
		//uniformCostSearch search = new uniformCostSearch(enState, goal, en);

		AStarSearch search = new AStarSearch(enState, goal, en);
		long startTime = System.nanoTime();

		path = search.start();

		long endTime = System.nanoTime();
>>>>>>> 019d77a77771e76dde96614a4375211a8033c8af
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		if (path != null) {
			System.out.println("Lenght of path: " + path.size());
		}

		/*
		 * List<EnviroState> allSeen = new ArrayList<EnviroState>();
		 * allSeen.add(enState); List<EnviroState> nextStates = new
		 * ArrayList<EnviroState>(); List<EnviroState> nextStates2 = new
		 * ArrayList<EnviroState>(); List<EnviroState> nextStates3 = new
		 * ArrayList<EnviroState>(); System.out.println("initial State");
		 * nextStates.addAll(en.getNext(enState));
		 * System.out.println(enState.toString()); for (EnviroState e : nextStates) {
		 * nextStates2.addAll(en.getNext(e)); } for (EnviroState e : nextStates2) {
		 * nextStates3.addAll(en.getNext(e)); }
		 * 
		 * System.out.println("first layer"); for (EnviroState e : nextStates) {
		 * System.out.println(e.toString()); if (!seen(allSeen, e)) allSeen.add(e); }
		 * System.out.println("second layer"); for (EnviroState e : nextStates2) {
		 * System.out.println(e.toString()); if (!seen(allSeen, e)) allSeen.add(e); }
		 * System.out.println("thrid layer"); for (EnviroState e : nextStates3) {
		 * System.out.println(e.toString()); if (!seen(allSeen, e)) allSeen.add(e); }
		 * 
		 * System.out.println("All seen"); for (EnviroState e : allSeen) {
		 * System.out.println(e.toString()); }
		 */

	}

	public boolean seen(List<EnviroState> allSeen, EnviroState newState) {
		for (EnviroState e : allSeen) {
			if (e.theSame(newState))
				return true;
		}
		return false;
	}

	public String nextAction(Collection<String> percepts) {
		if (path != null) {
			if (!path.empty()) {
				return path.pop();
			}
		}
		return "TURN_OFF";

	}
}
