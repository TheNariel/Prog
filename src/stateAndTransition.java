
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
	public String toString() {
		return String.format("Path cost: %d. Agent:%s at %dx%d %s Number of dirts: %d",pathCost, state.On, state.Agent.x, state.Agent.y, state.AgentOr, state.dirts.size());
	}
}
