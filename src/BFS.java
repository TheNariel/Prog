/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javier
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author javier
 */
public class BFS {

    List<stateAndTransition> frontier = new ArrayList<stateAndTransition>(); //list of frontiers
    //List<stateAndTransition> visitedNodes = new ArrayList<stateAndTransition>(); //list of visited nodes
    //List<String> path = new ArrayList<String>(); //array containing de path
    stateAndTransition root; //transcost, envirostate, stringmov, statestringparent
    EnviroState goal; //location of dirt, point agent, stringOr, boolean On
    Environment en; //width, height, pointhomeloc, string homeOr, list of obstacles
    List<stateAndTransition> seen = new ArrayList<stateAndTransition>();
    int stateExpancion = 0;
    Stack<String> path = new Stack<String>();

    public BFS(EnviroState root, EnviroState goal, Environment en) {

        this.goal = goal;
        this.root = new stateAndTransition(0, root, "", null); // (int transitionCost,EnviroState state,String movement,stateAndTransition parent) 
        this.root.parent = null;
        this.en = en;
    }

    public Stack<String> start() {

        /* INSERT(N0,FRONTIER)
      Repeat:
        If EMPTY?(FRONTIER) then return failure
        N= POP(FRONTIER)
        
        s= STATE(N) (expansion on N)
      If GOAL?(s) then return path or goal state
        For every state s’in SUCCESSORS(s)
        Create a new node N’as a child of N
        INSERT(N’,FRONTIER) */
        
        frontier.add(root); //add the root to the first position of the frontier
        stateAndTransition curr; //current state
        while (!frontier.isEmpty()) {
            curr = frontier.remove(0);
            seen.add(curr);

            //method o check if i already been there or not, if not continue de Do
            //method "thesame" method in envirostate 
            /* if (goal.theSame()){
                 }*/

            seen.add(curr); //add the root to the visitedNodes
            if (goal.theSame(curr.state)) {
                return getPath(curr);
            }

            for (stateAndTransition successor : en.getNextWitCost(curr.state, curr)) {
                boolean toAdd = true;
                stateExpancion++;
                for (stateAndTransition see : seen) {
                    if (see.state.theSame(successor.state)) {
                        //*if (see.pathCost <= successor.pathCost) //{
                            toAdd = false;
                            break;
                        //}
                    }
                }
                for (stateAndTransition see : frontier) { //changes
                    if (see.state.theSame(successor.state)) {
                        //*if (see.pathCost <= successor.pathCost) //{
                            toAdd = false;
                            break;
                        //}
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
