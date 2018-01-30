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

		} 
		Environment en = new Environment(width, height, new Point(x, y), orientation, obstacles);

		EnviroState enState = new EnviroState(dirt, new Point(x, y), orientation, false);
	
		EnviroState goal = new EnviroState(new ArrayList<Point>(), enState.Agent, enState.AgentOr, false);

				
		//lines co coment/uncoment to swich search algorithms.
		
		//BFS search = new BFS(enState, goal, en);
		//DFS search = new DFS(enState, goal, en);
		uniformCostSearch search = new uniformCostSearch(enState, goal, en);
		//AStarSearch search = new AStarSearch(enState, goal, en);
		
		path = search.start();



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
