
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

    public Environment(int width, int height, Point homeloc, String homeOr,List<Point> obstacles) {
        this.width = width;
        this.height = height;
        this.homeloc = homeloc;
        this.homeOr = homeOr;
        this.obstacles = obstacles;
    }
    
}
