/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezERD2016;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author junwu
 */

enum ezOBJStatus {selected, unselected, ready2move,  moving, ready2resize, resizing}

public abstract class ezOBJ extends Panel 
{
    ezOBJStatus status;
    ezOutline outline;
    public ezPage parent;
    Point op = null;
    TextField TempTextField;
    String str="Double Click to Edit";
    
    ezOBJ(ezPage p, int x, int y, int w, int h)
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parent = p;
        this.setSize(w, h);
        this.setLocation(x, y);
        status = ezOBJStatus.selected;
        outline = new ezOutline(this);
        showOutline();
        this.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if((status == ezOBJStatus.moving) ||  (status  == ezOBJStatus.ready2move))
                {
                    status = ezOBJStatus.moving;
                    int dx, dy;
                    
                    dx = e.getX() - op.x;
                    dy = e.getY() - op.y;
                    Point p =  ezOBJ.this.getLocation();
                    ezOBJ.this.setLocation(p.x+dx, p.y+dy);
                    if(TempTextField!=null)
                        TempTextField.setLocation(ezOBJ.this.getX() + 20, ezOBJ.this.getY() + ezOBJ.this.getHeight() / 2 - 15);
                }
            }
        });
        
        this.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                if (e.getClickCount() != 1) {
                    if (TempTextField != null)
                    {
                        parent.remove(TempTextField);
                    }
                    TempTextField = new TextField(ezOBJ.this.str);
                    TempTextField.setLocation(ezOBJ.this.getX() + 20, ezOBJ.this.getY() + ezOBJ.this.getHeight() / 2 - 15);
                    TempTextField.setSize(ezOBJ.this.getWidth() - 40, 30);
                    TempTextField.addFocusListener(new FocusListener() 
                    {
                        @Override public void focusLost(final FocusEvent pE) {}
                        @Override public void focusGained(final FocusEvent pE) 
                        {
                            TempTextField.selectAll();
                        }
                    });
                    TempTextField.addActionListener(new ActionListener() 
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) 
                        {
                            ezOBJ.this.str=TempTextField.getText();
                            parent.remove(TempTextField);
                            ezOBJ.this.repaint();
                        }
                    });
                    parent.add(TempTextField, 0);
                }
                if(status == ezOBJStatus.ready2move)
                {
                    status = ezOBJStatus.selected;
                    ezOBJ.this.showOutline();
                }
                else if(status == ezOBJStatus.moving)
                {
                    status =  ezOBJStatus.selected;
                    ezOBJ.this.showOutline();
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
            
            public void mousePressed(MouseEvent e)
            {
                if(status == ezOBJStatus.selected)
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    status =  ezOBJStatus.ready2move;
                    op = e.getPoint();
                    ezOBJ.this.hideOutline();
                   
                }
                else if(status == ezOBJStatus.unselected)
                {
                    if(ezOBJ.this.parent.activeOBJ == null)
                    {
                        ezOBJ.this.parent.activeOBJ = ezOBJ.this;
                        ezOBJ.this.showOutline();
                        ezOBJ.this.status = ezOBJStatus.selected;
                    }
                    else
                    {
                        ezOBJ.this.parent.activeOBJ.hideOutline();
                        ezOBJ.this.parent.activeOBJ.status =  ezOBJStatus.unselected;
                        ezOBJ.this.parent.activeOBJ = null;
                    
                        ezOBJ.this.showOutline();
                        ezOBJ.this.status = ezOBJStatus.selected;
                        ezOBJ.this.parent.activeOBJ = ezOBJ.this;
                    }
                }
             }
         });
    }

    public void paint(Graphics g)
    {
        draw(g);
    }
    
    abstract void draw(Graphics g);
    
    void showOutline()
    {
        outline.show();
    }
    
    void  hideOutline()
    {
        outline.hide();
    }
        
}
