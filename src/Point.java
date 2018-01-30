/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javier
 */
public class Point {
    
    int x;
    int y;
    

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(int x, int y){
        if(x == this.x && y == this.y)  {
            return true;
        }
        return false;
    }
    public boolean equals(Point p){
        if(p.x == this.x && p.y == this.y)  {
            return true;
        }
        return false;
    }
    public int manhattanDistance(Point p){
        return (Math.max(p.x, this.x)-Math.min(p.x, this.x))+(Math.max(p.y, this.y)-Math.min(p.y, this.y));
    }
    public Point copy() {
    	return new Point(x,y);
    }
    
}
