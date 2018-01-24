
public class stateAndTransition {
	EnviroState state;
	int transitionCost;
	String movement;
	stateAndTransition parent;
	public stateAndTransition(int transitionCost,EnviroState state,String movement,stateAndTransition parent) {
		
		this.state = state;
		this.transitionCost = transitionCost;
		this.movement = movement;
		this.parent = parent;
	}
}
