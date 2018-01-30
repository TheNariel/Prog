import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class uniformCostSearch {

	List<stateAndTransition> seen = new ArrayList<stateAndTransition>();
	PriorityQueue<stateAndTransition> frontier;
	Stack<String> path = new Stack<String>();
	stateAndTransition root; // transcost, envirostate, stringmov, statestringparent
	EnviroState goal; // location of dirt, point agent, stringOr, boolean On
	Environment en; // width, height, pointhomeloc, string homeOr, list of obstacles
	int maxFrontier = 0, stateExpancion = 0;

	public uniformCostSearch(EnviroState root, EnviroState goal, Environment en) {
		this.goal = goal;
		this.root = new stateAndTransition(0, root, "", null); // int transitionCost,EnviroState state,String
																// movement,stateAndTransition parent
		this.root.parent = null;
		this.en = en;
		frontier = new PriorityQueue<stateAndTransition>(20, new Comparator<stateAndTransition>() {
			// override compare method
			public int compare(stateAndTransition i, stateAndTransition j) {
				if (i.pathCost > j.pathCost) {
					return 1;
				}

				else if (i.pathCost < j.pathCost) {
					return -1;
				}

				else {
					return 0;
				}
			}
		});
	}

	public Stack<String> start() {
		frontier.add(root);
		stateAndTransition curr;
		while (!frontier.isEmpty()) {
			if (maxFrontier < frontier.size())
				maxFrontier = frontier.size();
			
			curr = frontier.poll();
			seen.add(curr);

			if (curr.state.theSame(goal)) {
				System.out.println("Succes");
				System.out.printf("Max frontier size: %d | State Expancion : %d", maxFrontier, stateExpancion);
				System.out.println();
				return getPath(curr);
			}
			
			
			stateExpancion++;
			List<stateAndTransition> successors=en.getNextWitCost(curr.state, curr);
			for (stateAndTransition successor :successors ) {
				boolean toAdd = true;
				
				for (stateAndTransition see : seen) {
					if (see.state.theSame(successor.state)) {
						toAdd = false;
						break;
					}
				}
				for (stateAndTransition see : frontier) {
					if (see.state.theSame(successor.state)) {
						if (see.pathCost <= successor.pathCost) {
							toAdd = false;
							break;
						}
					}
				}
				if (toAdd) {
					frontier.add(successor);
				}

			}

		}

		System.out.println("FAIL");
		System.out.printf("Max frontier size: %d | State Expancion : %d", maxFrontier, stateExpancion);
		return null;
	}

	private Stack<String> getPath(stateAndTransition curr) {
		path.clear();
		while (curr.parent != null) {
			path.add(curr.movement);
			curr = curr.parent;
		}
		return path;
	}

	/*
	 * public void sortFront() { frontier.sort(new Comparator<stateAndTransition>()
	 * {
	 * 
	 * @Override public int compare(stateAndTransition s1, stateAndTransition s2) {
	 * if (s1.pathCost == s2.pathCost) { return 0; } return s1.pathCost <
	 * s2.pathCost ? -1 : 1; } }); }
	 */
}
