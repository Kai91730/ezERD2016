/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezERD2016;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Thunder
 */
public class ezEntitySet extends ezOBJ
{
    Color color;
    ezEntitySet(ezPage p, int x, int y, int w, int h, Color c)
    {
        super(p, x, y, w, h);
        color = c;
    }
    
    void draw(Graphics g)
    {
        g.setColor(color);
        g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
        //g.setFont(new Font());
        g.drawString(str, (this.getWidth()/2)-str.length()*3, (this.getHeight()-1)/2);
    }
}
