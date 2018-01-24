
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
public class Environment {

	/*
	 * size of the grid location of obstacles (location of dirt)maybe Home location
	 * (home orientation)
	 */
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

	// check for proper coping of currentstat.dirts
	// siye of the state space #ofdirt ^2 * 4* grid size.
	public List<EnviroState> getNext(EnviroState currentState) {
		List<EnviroState> nextStates = new ArrayList<EnviroState>();
		EnviroState toAdd = checkOn(currentState);
		if (toAdd != null) {
			nextStates.add(toAdd);
		} else {
			toAdd = checkSuck(currentState);
			if (toAdd != null) {
				nextStates.add(toAdd);
			} else {
				toAdd = checkOff(currentState);
				if (toAdd != null) {
					nextStates.add(toAdd);
				} else {
					toAdd = checkGO(currentState);
					if (toAdd != null) {
						nextStates.add(toAdd);
					}
					toAdd = checkTurnRight(currentState);
					if (toAdd != null) {
						nextStates.add(toAdd);
					}
					toAdd = checkTurnLeft(currentState);
					if (toAdd != null) {
						nextStates.add(toAdd);
					}
				}

			}
		}

		return nextStates;

	}

	public List<stateAndTransition> getNextWitCost(EnviroState currentState,stateAndTransition parent) {
		List<stateAndTransition> nextStates = new ArrayList<stateAndTransition>();
		EnviroState toAdd = checkOn(currentState);
		if (toAdd != null) {
			nextStates.add(new stateAndTransition(0, toAdd,"on",parent));
		} else {
			toAdd = checkSuck(currentState);
			if (toAdd != null) {
				nextStates.add(new stateAndTransition(1, toAdd,"SUCK",parent));
			} else {
				toAdd = checkOff(currentState);
				if (toAdd != null) {
					nextStates.add(new stateAndTransition(0, toAdd,"OFF",parent));
				} else {
					toAdd = checkGO(currentState);
					if (toAdd != null) {
						nextStates.add(new stateAndTransition(2, toAdd,"GO",parent));
					}
					toAdd = checkTurnRight(currentState);
					if (toAdd != null) {
						nextStates.add(new stateAndTransition(3, toAdd,"TURN_RIGHT",parent));
					}
					toAdd = checkTurnLeft(currentState);
					if (toAdd != null) {
						nextStates.add(new stateAndTransition(3, toAdd,"TURN_LEFT",parent));
					}
				}

			}
		}

		return nextStates;

	}

	private EnviroState checkOn(EnviroState currentState) {
		if (!currentState.On) {
			return new EnviroState(currentState.dirts.subList(0, currentState.dirts.size()), currentState.Agent.copy(),
					currentState.AgentOr, true);
		}
		return null;

	}

	private EnviroState checkOff(EnviroState currentState) {
		if (currentState.dirts.isEmpty()) {
			if (currentState.Agent.equals(homeloc)) {
				return new EnviroState(currentState.dirts.subList(0, currentState.dirts.size()),
						currentState.Agent.copy(), currentState.AgentOr, false);
			}
		}
		return null;

	}

	private EnviroState checkTurnLeft(EnviroState currentState) {
		String nextOrientation = "";
		if (currentState.AgentOr.equals("NORTH")) {
			nextOrientation = "WEST";
		}
		if (currentState.AgentOr.equals("WEST")) {
			nextOrientation = "SOUTH";
		}
		if (currentState.AgentOr.equals("EAST")) {
			nextOrientation = "NORTH";
		}

		if (currentState.AgentOr.equals("SOUTH")) {
			nextOrientation = "EAST";
		}
		return new EnviroState(currentState.dirts.subList(0, currentState.dirts.size()), currentState.Agent.copy(),
				nextOrientation, currentState.On);
	}

	private EnviroState checkTurnRight(EnviroState currentState) {
		String nextOrientation = "";
		if (currentState.AgentOr.equals("NORTH")) {
			nextOrientation = "EAST";
		}
		if (currentState.AgentOr.equals("WEST")) {
			nextOrientation = "NORTH";
		}
		if (currentState.AgentOr.equals("EAST")) {
			nextOrientation = "SOUTH";
		}

		if (currentState.AgentOr.equals("SOUTH")) {
			nextOrientation = "WEST";
		}
		return new EnviroState(currentState.dirts.subList(0, currentState.dirts.size()), currentState.Agent.copy(),
				nextOrientation, currentState.On);
	}

	private EnviroState checkGO(EnviroState currentState) {

		int yFuture = currentState.Agent.y;
		int xFuture = currentState.Agent.x;
		boolean valid = true;

		if (currentState.AgentOr.equals("NORTH")) {
			yFuture++;
		}
		if (currentState.AgentOr.equals("WEST")) {
			xFuture--;
		}
		if (currentState.AgentOr.equals("EAST")) {
			xFuture++;
		}

		if (currentState.AgentOr.equals("SOUTH")) {
			yFuture--;
		}
		if (yFuture <= width && yFuture >= 1) {
			if (xFuture <= height && xFuture >= 1) {
				for (Point p : obstacles) {
					if (p.equals(xFuture, yFuture)) {
						valid = false;
						break;
					}

				}
			} else {
				valid = false;
			}

		} else {
			valid = false;
		}
		if (valid) {
			return new EnviroState(currentState.dirts.subList(0, currentState.dirts.size()),
					new Point(xFuture, yFuture), currentState.AgentOr, currentState.On);
		} else {
			return null;
		}

	}

	private EnviroState checkSuck(EnviroState currentState) {
		List<Point> nextDirt = currentState.dirts;
		int dirtToSuck=0;
		boolean valid = false;
		for (int p = 0;p<nextDirt.size();p++) {
			if (nextDirt.get(p).equals(currentState.Agent.x, currentState.Agent.y)) {
				dirtToSuck =p;
				valid = true;
				break;
			}

		}
		if (valid) {
			nextDirt.remove(dirtToSuck);
			return new EnviroState(nextDirt, currentState.Agent.copy(), currentState.AgentOr, currentState.On);
		} else {
			return null;
		}
	}

}

/*
 * if(currentState = (0, 0) North and East movements else
 * System.out.printf('Illegal movement'); if(currentState = (width, 0) North and
 * west movements else System.out.printf('Illegal movement'); if(currentState =
 * (height, 0) South and East movements else System.out.printf('Illegal
 * movement'); if(currentState = (width, heigth) West and south movements else
 * System.out.printf('Illegal movement');
 * 
 * 
 * }* /
 * 
 * } }
 * 
 * /*successor states: Go action: Position changes Turn right/left orientation
 * changes Turn on/off on/off Suck action: dirt is removed from a location legal
 * moves: Go action: when you do not hit a wall
 * 
 * Turn right/left only when the robot is on turn on/off only run on if robot is
 * off/only turn off is robot is on should only turn off when robot is back at
 * home location, when all dirt has been removed(GOAL) suck action when there is
 * dirt on robots location
 */
