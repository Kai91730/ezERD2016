/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezERD2016;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Thunder
 */
public class ezRelationship extends ezOBJ
{
    Color  color;
    
    ezRelationship(ezPage p, int x, int y, int w, int h, Color c)
    {
        super(p, x, y, w, h);
        color = c;
    }
    
    void draw(Graphics g)
    {
        g.setColor(color);
        int w = this.getWidth();
        int h = this.getHeight();
        g.drawLine(w/2, 0, w, h/2);
        g.drawLine(w,h/2, w/2, h);
        g.drawLine(w/2, h, 0, h/2);
        g.drawLine(0, h/2, w/2, 0);
        g.drawString(str, (this.getWidth()/2)-str.length()*3, (this.getHeight()-1)/2);
    }
}
