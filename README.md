<b>Project for Artificial intelligence class at University of Reykjavik. </b>

Assighment "prog1"

Problem DescriptionFind a good plan for the vacuum cleaner agent. The environment is very similar to the one in thefirst lab: It is a rectangular grid of cells each of which may contain dirt. Only now the grid may alsocontain obstacles. The agent is located in this grid and facing in one of the four directions: north,south, east or west.Here is a link to an example of what the environment might look like.The agent can execute the following actions:
•TURNON: This action initialises the robot and has to be executed first.

•TURNRIGHT, TURNLEFT: lets the robot rotate 90◦clockwise/counter-clockwise

•GO: lets the agent attempt to move to the next cell in the direction it is currently facing.

•SUCK: suck the dirt in the current cell

•TURNOFF: turns the robot off. Once turned off, it can only be turned on again after emptyingthe dust-container manually.


However, the agent now has complete information about the environment.  That is, the agentknows where it is initially, how big the environment is, where the obstacles are and which cells aredirty.  The goal is to clean all dirty cells, return to the initial location and turn off the robot whileminimizing the cost of all actions that were executed.Your actions have the following costs:

•1 + 50*D, if you TURNOFF the robot in the home location and there are D dirty cells left1

•100 + 50*D, if you TURNOFF the robot, but not in the home location and there are D dirtycells left

•5 for SUCK, if the current location of the robot does not contain dirt

•1 for SUCK, if the current location of the robot contains dirt•1 for all other actions

