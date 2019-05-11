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
public class ezTextBox extends ezOBJ
{
    Color color;
    
    ezJToolBar jtoolbar;
    
    ezTextBox(ezERD2016.ezPage p, int x, int y, int w, int h, Color c)
    {
        super(p, x, y, w, h);
        color  = c;
    }
    
    void draw(Graphics g)
    {
        g.setColor(color);
        //g.drawOval(0, 0, this.getWidth()-1, this.getHeight()-1);
        g.drawString(str, (this.getWidth()/2)-str.length()*3, (this.getHeight()-1)/2);
    }
}
