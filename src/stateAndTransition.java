
public class stateAndTransition {
	EnviroState state;
	int pathCost;
	String movement;
	stateAndTransition parent;
	public stateAndTransition(int transitionCost,EnviroState state,String movement,stateAndTransition parent) {
		
		this.state = state;
		this.pathCost = transitionCost;
		this.movement = movement;
		this.parent = parent;
	}
}
