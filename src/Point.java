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
    
}
