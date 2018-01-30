
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
public class EnviroState {

	/*
	 * state of the environment: location of the robot orientation of the robot
	 * location of dirts
	 */

	List<Point> dirts;
	Point Agent;
	String AgentOr;
	boolean On;

	public EnviroState(List<Point> dirts, Point Agent, String AgentOr, boolean On) {
		this.dirts = dirts;
		this.Agent = Agent;
		this.AgentOr = AgentOr;
		this.On = On;
	}

	public String toString() {
		return String.format("Agent:%s at %dx%d %s Number of dirts: %d", On, Agent.x, Agent.y, AgentOr, dirts.size());
	}

	public boolean theSame(EnviroState comp) {
		if (this.On != comp.On)
			return false;
		if (!this.Agent.equals(comp.Agent))
			return false;
		if (!this.AgentOr.equals(comp.AgentOr))
			return false;
		if (this.dirts.size() != comp.dirts.size()) {
			return false;
		} 
		else {
			List<Point> temp = this.dirts.subList(0, this.dirts.size());
			temp.retainAll(comp.dirts);
			if (temp.size() != comp.dirts.size())
				return false;
		}

		return true;
	}

}
