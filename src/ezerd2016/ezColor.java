/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezERD2016;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;


/**
 *
 * @author Thunder
 */
public class ezColor extends JFrame
{
    Color color;
    ezJToolBar parent;
    public JColorChooser jcolorchooser;
    
    public ezColor(ezJToolBar p) 
    {
        super();
        parent = p;
        
        Color c = JColorChooser.showDialog(ezColor.this,"Choose a color...", getBackground());
        if(c!=null)
        {
            System.out.println(c);
        
            parent.R=0;
            parent.G=0;
            parent.B=0;
                
            parent.R=c.getRed();
            parent.G=c.getGreen();
            parent.B=c.getBlue();
            
            color = c;
            //System.out.println(c);
            //Color color = new Color(r,g,b);
            //System.out.println(p.R);
        }
    }
    
}