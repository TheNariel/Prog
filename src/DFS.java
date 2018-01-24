
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author javier
 */
public class DFS {

    List<stateAndTransition> frontier = new ArrayList<stateAndTransition>(); //list of frontiers
    List<stateAndTransition> visitedNodes = new ArrayList<stateAndTransition>(); //list of visited nodes
    List<String> path = new ArrayList<String>(); //array containing de path
    stateAndTransition root; //transcost, envirostate, stringmov, statestringparent
    EnviroState goal; //location of dirt, point agent, stringOr, boolean On
    Environment en; //width, height, pointhomeloc, string homeOr, list of obstacles

    public DFS(EnviroState root, EnviroState goal, Environment en) {

        this.goal = goal;
        this.root = new stateAndTransition(0, root, "", null); // (int transitionCost,EnviroState state,String movement,stateAndTransition parent) 
        this.root.parent = null;
        this.en = en;
    }

    public List<String> start() {
        frontier.add(root); //add the root to the first position of the frontier
        stateAndTransition curr; //current state
        while (!frontier.isEmpty()) {
            curr = frontier.remove(0);
            //method o check if i already been there or not, if not continue de Do
            
            //method "thesame" method in envirostate 
            
            visitedNodes.add(curr); //add the root to the visitedNodes
            if (goal.equals(curr.state)) {
                return getPath(curr);
            }

            frontier.addAll(en.getNextWitCost(curr.state, curr));
        }
        return null;

    }

    private List<String> getPath(stateAndTransition curr) {
        path.clear();
        while (curr.parent != null) {
            path.add(curr.movement);
            curr = curr.parent;
        }
        return path;
    }

  
        
}
