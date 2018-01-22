
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
public class Environment {

    /* size of the grid
	location of obstacles
	(location of dirt)maybe
	Home location
	(home orientation) */
    int width;
    int height;

    Point homeloc;
    String homeOr;
    List<Point> obstacles;

    public Environment(int width, int height, Point homeloc, String homeOr, List<Point> obstacles) {
        this.width = width;
        this.height = height;
        this.homeloc = homeloc;
        this.homeOr = homeOr;
        this.obstacles = obstacles;
    }

    public void getNext(EnviroState currentState) {
        int yFuture = currentState.Agent.y;
        int xFuture = currentState.Agent.x;
        boolean valid = true;

        if (currentState.AgentOr.equals("NORTH")) {
            yFuture++;
        }
        if (currentState.AgentOr.equals("WEAST")) {
            xFuture--;
        }
        if (currentState.AgentOr.equals("EAST")) {
            xFuture++;
        }

        if (currentState.AgentOr.equals("SOUTH")) {
            yFuture--;
        }
        if (yFuture <= width || yFuture >= 1) {
            if (xFuture <= height || xFuture >= 1) {
                for (Point p : obstacles) {
                    if (p.equals(xFuture, yFuture)) {
                        valid = false;
                        break;
                    }

                }
            } else{ valid = false;
            }

        }else{ valid = false;
        }

    }
}
   

    
    
    
    
    
    /*if(currentState = (0, 0) North and East movements
        else System.out.printf('Illegal movement');
        if(currentState = (width, 0) North and west movements
        else System.out.printf('Illegal movement');
        if(currentState = (height, 0) South and East movements
        else System.out.printf('Illegal movement');
        if(currentState = (width, heigth) West and south movements 
        else System.out.printf('Illegal movement');

            
        }* / 
        
    }
}

/*successor states:
	Go action:
		Position changes
	Turn right/left
		orientation changes 
	Turn on/off
		on/off
	Suck action:
		dirt is removed from a location
	legal moves:
		Go action:
			when you do not hit a wall

		Turn right/left
			only when the robot is on
		turn on/off
			only run on if robot is off/only turn off is robot is on
			should only turn off when robot is back at home location,
			when all dirt has been removed(GOAL)
		suck action
			when there is dirt on robots location */
