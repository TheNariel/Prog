import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class uniformCostSearch {

	List<stateAndTransition> frontier = new ArrayList<stateAndTransition>();
	List<String> path = new ArrayList<String>();
	stateAndTransition root; //transcost, envirostate, stringmov, statestringparent
	EnviroState goal; //location of dirt, point agent, stringOr, boolean On
	Environment en; //width, height, pointhomeloc, string homeOr, list of obstacles

	public uniformCostSearch(EnviroState root, EnviroState goal, Environment en) {
		this.goal = goal;
		this.root = new stateAndTransition(0, root, "",null); //int transitionCost,EnviroState state,String movement,stateAndTransition parent
		this.root.parent = null;
		this.en = en;
	}

	public List<String> start() {
		frontier.add(root);
		stateAndTransition curr;
		while (!frontier.isEmpty()) {
			sortFront();
			curr = frontier.remove(0);
			if (goal.equals(curr.state))
				return getPath(curr);

			frontier.addAll(en.getNextWitCost(curr.state, curr));

		}
		return null;
	}
	private List<String> getPath(stateAndTransition curr){
		path.clear();
		while(curr.parent!=null) {
			path.add(curr.movement);
			curr=curr.parent;
		}
		return path;
	}

	public void sortFront() {
		frontier.sort(new Comparator<stateAndTransition>() {
			@Override
			public int compare(stateAndTransition s1, stateAndTransition s2) {
				if (s1.pathCost == s2.pathCost) {
					return 0;
				}
				return s1.pathCost < s2.pathCost ? -1 : 1;
			}
		});
	}
}
