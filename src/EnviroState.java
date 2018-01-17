
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
    
       /* state of the environment:
	location of the robot
	orientation of the robot
	location of dirts */
    
    List<Point> dirts; 
    List<Point> Agents;
    String AgentOr;
    boolean On;

    public EnviroState(List<Point> dirts, List<Point> Agents, String AgentOr, boolean On) {
        this.dirts = dirts;
        this.Agents = Agents;
        this.AgentOr = AgentOr;
        this.On = On;
    }
    
}
