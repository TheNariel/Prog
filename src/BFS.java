
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author javier
 */
public class BFS {

    List<stateAndTransition> frontier = new ArrayList<stateAndTransition>(); //list of frontiers
    stateAndTransition root; //transcost, envirostate, stringmov, statestringparent
    EnviroState goal; //location of dirt, point agent, stringOr, boolean On
    Environment en; //width, height, pointhomeloc, string homeOr, list of obstacles
    List<stateAndTransition> seen = new ArrayList<stateAndTransition>();
	int maxFrontier = 0, stateExpancion = 0;
    Stack<String> path = new Stack<String>();

    public BFS(EnviroState root, EnviroState goal, Environment en) {

        this.goal = goal;
        this.root = new stateAndTransition(0, root, "", null); // (int transitionCost,EnviroState state,String movement,stateAndTransition parent) 
        this.root.parent = null;
        this.en = en;
    }

    public Stack<String> start() {

        
        frontier.add(root); //add the root to the first position of the frontier
        stateAndTransition curr; //current state
        while (!frontier.isEmpty()) {
    		if (maxFrontier < frontier.size())
				maxFrontier = frontier.size();
			
            curr = frontier.remove(0);
            seen.add(curr);

            seen.add(curr); //add the root to the visitedNodes
            if (goal.theSame(curr.state)) {
            	System.out.println("Succes");
				System.out.printf("Max frontier size: %d | State Expancion : %d", maxFrontier, stateExpancion);
				System.out.println();
                return getPath(curr);
            }
			stateExpancion++;
            for (stateAndTransition successor : en.getNext(curr.state, curr)) {
                boolean toAdd = true;
               
                for (stateAndTransition see : seen) {
                    if (see.state.theSame(successor.state)) {
                            toAdd = false;
                            break;
                    }
                }
                for (stateAndTransition see : frontier) { //changes
                    if (see.state.theSame(successor.state)) {
                            toAdd = false;
                            break;
                    }
                }
                if (toAdd) {
                    frontier.add(successor);
                   
                }

            }

        }

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

}
