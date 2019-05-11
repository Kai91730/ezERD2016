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
 * @author Thunder
 */
public class ezOutline 
{
    ezOBJ parent;
    Point lp;
    Point cp = null;
    Panel E, W, S, N, NE, SE, SW, NW;
    int SElx, SEly, NEx, NEy, NElx, NEly, NWx, NWy, NWlx, NWly, SWx, SWy, SWlx, SWly;
    int Sx, Sy, Slx, Sly, Nx, Ny, Nlx, Nly, Wx, Wy, Wlx, Wly, Ex, Ey, Elx, Ely;
    
    ezOutline(ezOBJ p)
    {
        parent = p;
        lp = new Point();
        cp = new Point();
        
        SE = new Panel();
        SE.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        SE.setBackground(Color.red);
        SE.setSize(7, 7);
        SE.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y + parent.getHeight() + 2);
        parent.parent.add(SE);
        
        E = new Panel();
        E.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        E.setBackground(Color.red);
        E.setSize(7, 7);
        E.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y + (parent.getHeight()/2) - 3);
        parent.parent.add(E);
        
        NE = new Panel();
        NE.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        NE.setBackground(Color.red);
        NE.setSize(7, 7);
        NE.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y - 8);
        parent.parent.add(NE);
        
        N = new Panel();
        N.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        N.setBackground(Color.red);
        N.setSize(7, 7);
        N.setLocation(parent.getLocation().x + (parent.getWidth()/2) - 3, parent.getLocation().y - 8);
        parent.parent.add(N);
        
        NW = new Panel();
        NW.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        NW.setBackground(Color.red);
        NW.setSize(7, 7);
        NW.setLocation(parent.getLocation().x - 8, parent.getLocation().y - 8);
        parent.parent.add(NW);
        
        W = new Panel();
        W.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        W.setBackground(Color.red);
        W.setSize(7, 7);
        W.setLocation(parent.getLocation().x - 8, parent.getLocation().y + (parent.getHeight()/2));
        parent.parent.add(W);
        
        SW = new Panel();
        SW.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        SW.setBackground(Color.red);
        SW.setSize(7, 7);
        SW.setLocation(parent.getLocation().x - 8, parent.getLocation().y + parent.getHeight() + 2);
        parent.parent.add(SW);
        
        S = new Panel();
        S.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        S.setBackground(Color.red);
        S.setSize(7, 7);
        S.setLocation(parent.getLocation().x + (parent.getWidth()/2), parent.getLocation().y + parent.getHeight() + 2);
        parent.parent.add(S);
        
        SE.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    parent.setSize(parent.getWidth() + dx, parent.getHeight() + dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing  && parent.parent.getMousePosition()!=null)
                {
                    if(parent.parent.getMousePosition().x > SElx && parent.parent.getMousePosition().y > SEly)
                    {
                        parent.setLocation(SElx, SEly);
                        parent.setSize(parent.parent.getMousePosition().x - SElx, parent.parent.getMousePosition().y - SEly);
                    }
                    else if(parent.parent.getMousePosition().x < SElx && parent.parent.getMousePosition().y < SEly)
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, parent.parent.getMousePosition().y);
                        parent.setSize(SElx - parent.parent.getMousePosition().x, SEly - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x > SElx && parent.parent.getMousePosition().y < SEly)
                    {
                        parent.setLocation(SElx, parent.parent.getMousePosition().y);
                        parent.setSize(parent.parent.getMousePosition().x - SElx, SEly - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x < SElx && parent.parent.getMousePosition().y > SEly)
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, SEly);
                        parent.setSize(SElx - parent.parent.getMousePosition().x,parent.parent.getMousePosition().y -SEly);
                    }
                }
             }
        });
       
        SE.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    SElx = parent.getLocation().x;
                    SEly = parent.getLocation().y;
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        });
        NE.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth() + dx, parent.getHeight() + dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing  && parent.parent.getMousePosition()!=null)
                {
                    if(parent.parent.getMousePosition().x > NElx && parent.parent.getMousePosition().y < (NEly + NEy))
                    {
                        parent.setLocation(parent.getLocation().x, (parent.parent.getMousePosition().y));
                        parent.setSize(parent.parent.getMousePosition().x - NElx, NEy + NEly - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x < NElx && parent.parent.getMousePosition().y < (NEly + NEy))
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, parent.parent.getMousePosition().y);
                        parent.setSize(NElx - parent.parent.getMousePosition().x, (NEy + NEly) - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x < NElx && parent.parent.getMousePosition().y > (NEly + NEy))
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, NEly + NEy);
                        parent.setSize(NElx - parent.parent.getMousePosition().x, parent.parent.getMousePosition().y - (NEy + NEly));
                    }
                    else if(parent.parent.getMousePosition().x > NElx && parent.parent.getMousePosition().y > (NEly + NEy))
                    {
                        parent.setLocation(NElx, (NEy + NEly));
                        parent.setSize(parent.parent.getMousePosition().x - NElx, parent.parent.getMousePosition().y - (NEy + NEly));
                    }
                }
             }
        });
       
        NE.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    NEx = parent.getWidth();
                    NEy = parent.getHeight();
                    NElx = parent.getLocation().x;
                    NEly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                    //System.out.println("(" + e.getX() + "," + e.getY() + ")");
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        NW.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth() + dx, parent.getHeight() + dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    if(parent.parent.getMousePosition().x < (NWx + NWlx) && parent.parent.getMousePosition().y < (NWy + NWly))
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, parent.parent.getMousePosition().y);
                        parent.setSize((NWlx + NWx) - parent.parent.getMousePosition().x, (NWly + NWy) - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x < (NWx + NWlx) && parent.parent.getMousePosition().y > (NWy + NWly))
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, (NWly + NWy));
                        parent.setSize((NWlx + NWx) - parent.parent.getMousePosition().x, parent.parent.getMousePosition().y -  (NWly + NWy));
                    }
                    else if(parent.parent.getMousePosition().x > (NWx + NWlx) && parent.parent.getMousePosition().y > (NWy + NWly))
                    {
                        parent.setLocation((NWlx + NWx), (NWly + NWy));
                        parent.setSize(parent.parent.getMousePosition().x - (NWlx + NWx), parent.parent.getMousePosition().y -  (NWly + NWy));
                    }
                    else if(parent.parent.getMousePosition().x > (NWx + NWlx) && parent.parent.getMousePosition().y < (NWy + NWly))
                    {
                        parent.setLocation((NWlx + NWx), parent.parent.getMousePosition().y);
                        parent.setSize(parent.parent.getMousePosition().x - (NWlx + NWx), (NWly + NWy) - parent.parent.getMousePosition().y);
                    }
                }
             }
        });
       
        NW.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    NWx = parent.getWidth();
                    NWy = parent.getHeight();
                    NWlx = parent.getLocation().x;
                    NWly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        SW.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth() + dx, parent.getHeight() + dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    if(parent.parent.getMousePosition().x < (SWlx + SWx) && parent.parent.getMousePosition().y > SWly)
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, SWly);
                        parent.setSize((SWlx + SWx) - parent.parent.getMousePosition().x, parent.parent.getMousePosition().y - SWly);
                    }
                    else if(parent.parent.getMousePosition().x > (SWlx + SWx) && parent.parent.getMousePosition().y > SWly)
                    {
                        parent.setLocation((SWlx + SWx), SWly);
                        parent.setSize(parent.parent.getMousePosition().x - (SWlx + SWx), parent.parent.getMousePosition().y - SWly);
                    }
                    else if(parent.parent.getMousePosition().x > (SWlx + SWx) && parent.parent.getMousePosition().y < SWly)
                    {
                        parent.setLocation((SWlx + SWx), parent.parent.getMousePosition().y);
                        parent.setSize(parent.parent.getMousePosition().x - (SWlx + SWx), SWly - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().x < (SWlx + SWx) && parent.parent.getMousePosition().y < SWly)
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, parent.parent.getMousePosition().y);
                        parent.setSize((SWlx + SWx) - parent.parent.getMousePosition().x, SWly - parent.parent.getMousePosition().y);
                    }
                }
             }
        });
       
        SW.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    SWx = parent.getWidth();
                    SWy = parent.getHeight();
                    SWlx = parent.getLocation().x;
                    SWly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        S.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth(), parent.getHeight() - dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    if(parent.parent.getMousePosition().y > Sly)
                    {
                        parent.setSize(Sx, parent.parent.getMousePosition().y - Sly);
                    }
                    else if(parent.parent.getMousePosition().y < Sly)
                    {
                        parent.setLocation(Slx, parent.parent.getMousePosition().y);
                        parent.setSize(Sx, Sly - parent.parent.getMousePosition().y);
                    }
                }
             }
        });
       
        S.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    Sx = parent.getWidth();
                    Sy = parent.getHeight();
                    Slx = parent.getLocation().x;
                    Sly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                    //System.out.println("(" + e.getX() + "," + e.getY() + ")");
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        
        
        N.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth(), parent.getHeight() - dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    if(parent.parent.getMousePosition().y < (Ny + Nly))
                    {
                        parent.setLocation(Nlx, parent.parent.getMousePosition().y);
                        parent.setSize(Nx, (Ny + Nly) - parent.parent.getMousePosition().y);
                    }
                    else if(parent.parent.getMousePosition().y > (Ny + Nly))
                    {
                        parent.setLocation(Nlx, (Nly + Ny));
                        parent.setSize(Nx, parent.parent.getMousePosition().y - (Ny + Nly));
                    }
                    
                }
             }
        });
       
        N.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    Nx = parent.getWidth();
                    Ny = parent.getHeight();
                    Nlx = parent.getLocation().x;
                    Nly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                    //System.out.println("(" + e.getX() + "," + e.getY() + ")");
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        
        E.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth(), parent.getHeight() - dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    //System.out.println(parent.getLocation());
                    //parent.setLocation(parent.parent.getMousePosition().x, parent.getLocation().y);
                    if(parent.parent.getMousePosition().x > Elx)
                    {
                        parent.setSize(parent.parent.getMousePosition().x - Elx, parent.getHeight());
                    }
                    else if(parent.parent.getMousePosition().x < Elx)
                    {
                        parent.setLocation(parent.parent.getMousePosition().x,Ely);
                        parent.setSize(Elx - parent.parent.getMousePosition().x,Ey);
                    }
                }
             }
        });
       
        E.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    Ex = parent.getWidth();
                    Ey = parent.getHeight();
                    Elx = parent.getLocation().x;
                    Ely = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
        
        W.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                
                int dx, dy;
                if(parent.status == ezOBJStatus.ready2resize)
                {
                    
                    parent.status = ezOBJStatus.resizing;
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    
                    parent.setSize(parent.getWidth(), parent.getHeight() - dy);
                    lp = e.getPoint();
                }
                else if(parent.status == ezOBJStatus.resizing && parent.parent.getMousePosition()!=null)
                {
                    dx = e.getX() - lp.x;
                    dy = e.getY() - lp.y;
                    //System.out.println(parent.getLocation());
                    if(parent.parent.getMousePosition().x < (Wlx + Wx))
                    {
                        parent.setLocation(parent.parent.getMousePosition().x, Wly);
                        parent.setSize((Wx + Wlx) - parent.parent.getMousePosition().x, Wy);
                    }
                    else if(parent.parent.getMousePosition().x > (Wlx + Wx))
                    {
                        parent.setLocation((Wx + Wlx), Wly);
                        parent.setSize(parent.parent.getMousePosition().x - (Wlx +Wx), Wy);
                    }
                }
            }
        });
       
        W.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(parent.status == ezOBJStatus.selected)
                {
                    Wx = parent.getWidth();
                    Wy = parent.getHeight();
                    Wlx = parent.getLocation().x;
                    Wly = parent.getLocation().y;
                    cp = e.getPoint();
                    parent.status = ezOBJStatus.ready2resize;
                    parent.hideOutline();
                    lp = e.getPoint();
                    parent.setVisible(true);
                }
            }
            public void mouseReleased(MouseEvent e)
           {
                if(parent.status == ezOBJStatus.resizing)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
                else if(parent.status == ezOBJStatus.ready2resize)
                {
                    parent.status = ezOBJStatus.selected;
                    parent.setVisible(true);
                    parent.showOutline();
                }
            }
        }); 
    }
    
    void show()
    {
        Graphics g = parent.parent.getGraphics();
        //Graphics2D g2 = (Graphics2D) g;
        g.setXORMode(Color.GRAY);
        g.drawRect(parent.getLocation().x-5, parent.getLocation().y-5, parent.getSize().width+10, parent.getSize().height+10);
        SE.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y + parent.getHeight() + 2);
        E.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y + (parent.getHeight()/2) - 3);
        NE.setLocation(parent.getLocation().x + parent.getWidth() + 2, parent.getLocation().y - 8);
        N.setLocation(parent.getLocation().x + (parent.getWidth()/2) - 3, parent.getLocation().y - 8);
        NW.setLocation(parent.getLocation().x - 8, parent.getLocation().y - 8);
        W.setLocation(parent.getLocation().x - 8, parent.getLocation().y + (parent.getHeight()/2) - 3);
        SW.setLocation(parent.getLocation().x - 8, parent.getLocation().y + parent.getHeight() + 2);
        S.setLocation(parent.getLocation().x + (parent.getWidth()/2) - 3, parent.getLocation().y + parent.getHeight() + 2);
        
        SE.setVisible(true);
        E.setVisible(true);
        NE.setVisible(true);
        N.setVisible(true);
        NW.setVisible(true);
        W.setVisible(true);
        SW.setVisible(true);
        S.setVisible(true);
    }
    
    void hide()
    {
        Graphics g = parent.parent.getGraphics();
        g.setXORMode(Color.GRAY);
        g.drawRect(parent.getLocation().x-5, parent.getLocation().y-5, parent.getSize().width+10, parent.getSize().height+10);
        SE.setVisible(false);
        E.setVisible(false);
        NE.setVisible(false);
        N.setVisible(false);
        NW.setVisible(false);
        W.setVisible(false);
        SW.setVisible(false);
        S.setVisible(false);
    }
}
