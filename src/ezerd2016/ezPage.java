package ezERD2016;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
/**
 *
 * @author Thunder
 */

enum ezPageStatus{activated, inactivated, ready2draw, drawing, ready2creatOBJ, creatingOBJ, ready2erase, erasing, lining, ready2line}

public class ezPage extends Panel
{
    //static Color c[] = {Color.BLACK};
    //static int count = 0;
    
    boolean unselectionOBJ = false;
    ezOBJ  activeOBJ;
    ezColor ezcolor;
    
    ezPageStatus status;
    
    Point lp, cp;
    Color c = Color.BLACK;
    Vector<ezDrawLine> drawLines;
    ezJToolBar parent;
   
    

    public ezPage(ezJToolBar p)
    {
        super();
        parent = p;
        
       
        status = ezPageStatus.activated;
        drawLines = new Vector<ezDrawLine>();
        this.setLayout(null);
        
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if(status == ezPageStatus.drawing)
                {
                    //int count = 0;
                    Color c = new Color(p.R, p.G, p.B);
                    cp=e.getPoint();
                    
                    Graphics g=ezPage.this.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(parent.linecolor);
                    if(parent.input > 0 && parent.input < 9)
                    {
                        Stroke stroke=new BasicStroke(parent.input);
                        g2.setStroke(stroke);
                        //System.out.println(p.R);
                        g2.drawLine(lp.x, lp.y, cp.x, cp.y);
                        drawLines.add(new ezDrawLine(lp, cp, c, parent.input));
                    }
                    else
                    {
                        parent.input = 1;
                        Stroke stroke=new BasicStroke(parent.input);
                        g2.setStroke(stroke);
                        //System.out.println(p.R);
                        g2.drawLine(lp.x, lp.y, cp.x, cp.y);
                        drawLines.add(new ezDrawLine(lp, cp, c, parent.input));
                    }
                    lp=cp;
                }
                else if(status == ezPageStatus.erasing)
                {
                    //int count = 10;
                    Color c = Color.WHITE;
                    cp=e.getPoint();
                    Graphics g = ezPage.this.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(c);
                    //count = 3;
                    Stroke stroke = new BasicStroke(15.0F);
                    g2.setStroke(stroke);
                    //System.out.println(p.R);
                    g2.drawLine(lp.x, lp.y, cp.x, cp.y);
                    drawLines.add(new ezDrawLine(lp, cp, c, 15));
                    lp=cp;
                }
                else if(status==ezPageStatus.creatingOBJ)
                {
                    Graphics g=ezPage.this.getGraphics();
                    if(cp!=null)
                    {
                        //System.out.println("null");
                        if(lp.x<cp.x && lp.y>cp.y)
                        {
                            //System.out.println("First");
                            g.setXORMode(Color.WHITE);
                            g.drawRect(lp.x, cp.y, cp.x-lp.x, lp.y-cp.y);
                        }
                        else if(lp.x>cp.x && lp.y>cp.y)
                        {
                            //System.out.println("Second");
                            g.setXORMode(Color.WHITE);
                            g.drawRect(cp.x, cp.y, lp.x-cp.x, lp.y-cp.y);
                        }
                        else if(lp.x>cp.x && lp.y<cp.y)
                        {
                            //System.out.println("Third");
                            g.setXORMode(Color.WHITE);
                            g.drawRect(cp.x, lp.y, lp.x-cp.x, cp.y-lp.y);
                        }
                        else if(lp.x<cp.x && lp.y<cp.y)
                        {
                            //System.out.println("Fourth");
                            g.setXORMode(Color.WHITE);             
                            g.drawRect(lp.x, lp.y, cp.x-lp.x, cp.y-lp.y);
                        }
                    }
                    cp=e.getPoint();
                    if(lp.x<cp.x && lp.y>cp.y)
                    {
                        //System.out.println("First");
                        g.setXORMode(Color.WHITE);
                        g.drawRect(lp.x, cp.y, cp.x-lp.x, lp.y-cp.y);
                    }
                    else if(lp.x>cp.x && lp.y>cp.y)
                    {
                        //System.out.println("Second");
                        g.setXORMode(Color.WHITE);
                        g.drawRect(cp.x, cp.y, lp.x-cp.x, lp.y-cp.y);
                    }
                    else if(lp.x>cp.x && lp.y<cp.y)
                    {
                        //System.out.println("Third");
                        g.setXORMode(Color.WHITE);
                        g.drawRect(cp.x, lp.y, lp.x-cp.x, cp.y-lp.y);
                    }
                    else if(lp.x<cp.x && lp.y<cp.y)
                    {
                        //System.out.println("Fourth");
                        g.setXORMode(Color.WHITE);             
                        g.drawRect(lp.x, lp.y, cp.x-lp.x, cp.y-lp.y);
                    }
                }
            }
        });
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(activeOBJ != null)
                {
                    if(activeOBJ.status==ezOBJStatus.selected)
                    {
                        activeOBJ.hideOutline();
                        activeOBJ.status = ezOBJStatus.unselected;
                        if(activeOBJ.TempTextField != null)
                        {
                            ezPage.this.remove(activeOBJ.TempTextField);
                        }
                        activeOBJ = null;
                    }
                }
                if(status == ezPageStatus.ready2draw)
                {
                    lp = e.getPoint();
                    status = ezPageStatus.drawing;
                }
                else if(status == ezPageStatus.ready2erase)
                {
                    lp = e.getPoint();
                    status = ezPageStatus.erasing;
                }
                else if(status == ezPageStatus.ready2line)
                {
                    lp = e.getPoint();
                    status = ezPageStatus.lining;
                }
                else if(status == ezPageStatus.ready2creatOBJ)
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    lp = e.getPoint();
                    status = ezPageStatus.creatingOBJ; 
                    cp = null;
                }
            }
            
            public void mouseReleased(MouseEvent e)
            {
                if(status == ezPageStatus.drawing)
                {
                    status = ezPageStatus.ready2draw;
                }
                else if(status == ezPageStatus.erasing)
                {
                    status = ezPageStatus.ready2erase;
                }
                else if(status == ezPageStatus.lining)
                {
                    int count = 0;
                    Color c = new Color(p.R, p.G, p.B);
                    cp=e.getPoint();
                    
                    Graphics g=ezPage.this.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(parent.linecolor);
                    if(parent.input > 0 && parent.input < 9)
                    {
                        Stroke stroke=new BasicStroke(parent.input);
                        g2.setStroke(stroke);
                        //System.out.println(p.R);
                        g2.drawLine(lp.x, lp.y, cp.x, cp.y);
                        drawLines.add(new ezDrawLine(lp, cp, c, parent.input));
                    }
                    else
                    {
                        parent.input = 1;
                        Stroke stroke=new BasicStroke(parent.input);
                        g2.setStroke(stroke);
                        //System.out.println(p.R);
                        g2.drawLine(lp.x, lp.y, cp.x, cp.y);
                        drawLines.add(new ezDrawLine(lp, cp, c, parent.input));
                    }
                    lp=cp;
                    status = ezPageStatus.ready2line;
                }
                else if(status == ezPageStatus.creatingOBJ)
                {
                    if(!((lp.x == e.getPoint().x) && (lp.y == e.getPoint().y)))
                    {
                        Graphics g = ezPage.this.getGraphics();
                        if(cp != null)
                        {
                            if(lp.x<cp.x && lp.y>cp.y)
                            {
                                //System.out.println("First");
                                g.setXORMode(Color.WHITE);
                                g.drawRect(lp.x, cp.y, cp.x-lp.x, lp.y-cp.y);
                            }
                            else if(lp.x>cp.x && lp.y>cp.y)
                            {
                                //System.out.println("Second");
                                g.setXORMode(Color.WHITE);
                                g.drawRect(cp.x, cp.y, lp.x-cp.x, lp.y-cp.y);
                            }
                            else if(lp.x>cp.x && lp.y<cp.y)
                            {
                                //System.out.println("Third");
                                g.setXORMode(Color.WHITE);
                                g.drawRect(cp.x, lp.y, lp.x-cp.x, cp.y-lp.y);
                            }
                            else if(lp.x<cp.x && lp.y<cp.y)
                            {
                                //System.out.println("Fourth");
                                g.setXORMode(Color.WHITE);             
                                g.drawRect(lp.x, lp.y, cp.x-lp.x, cp.y-lp.y);
                            }               
                        }
                        //System.out.println("cp:" + cp + "lp" + lp);
                        status = ezPageStatus.ready2creatOBJ;
                    
                        ezOBJ ezo = null;
                        
                        if(cp != null)
                        {
                            int width = 0;
                            int height = 0;
                            int x = 0;
                            int y = 0;
                            
                            if(lp.x<cp.x && lp.y>cp.y)
                            {
                                width = cp.x-lp.x;
                                height = lp.y-cp.y;
                                x = lp.x;
                                y = cp.y;
                                //g.drawRect(lp.x, cp.y, cp.x-lp.x, lp.y-cp.y);
                            }
                            else if(lp.x>cp.x && lp.y>cp.y)
                            {
                                //System.out.println("Second");
                                width =lp.x-cp.x;
                                height = lp.y-cp.y;
                                x = cp.x;
                                y = cp.y;
                                //g.drawRect(cp.x, cp.y, lp.x-cp.x, lp.y-cp.y);
                            }
                            else if(lp.x>cp.x && lp.y<cp.y)
                            {
                                //System.out.println("Third");
                                width = lp.x-cp.x;
                                height = cp.y-lp.y;
                                x = cp.x;
                                y = lp.y;
                                //g.drawRect(cp.x, lp.y, lp.x-cp.x, cp.y-lp.y);
                            }
                            else if(lp.x<cp.x && lp.y<cp.y)
                            {
                                //System.out.println("Fourth");
                                width = cp.x-lp.x;
                                height = cp.y-lp.y;
                                x = lp.x;
                                y = lp.y;
                                //g.drawRect(lp.x, lp.y, cp.x-lp.x, cp.y-lp.y);
                            }
                           
                        
                            if(ezPage.this.parent.objType == ezOBJType.AT)
                            {
                                Color c = new Color(p.R, p.G, p.B);
                                ezo = new ezAttribute(ezPage.this, x, y, width, height, c);
                            }
                            else if(ezPage.this.parent.objType == ezOBJType.ES)
                            {
                                Color c = new Color(p.R, p.G, p.B);
                                ezo = new ezEntitySet(ezPage.this, x, y, width, height, c);
                            }
                            else if(ezPage.this.parent.objType == ezOBJType.RS)
                            {
                                Color c = new Color(p.R, p.G, p.B);
                                ezo = new ezRelationship(ezPage.this, x, y, width, height, c);
                            }
                            else if(ezPage.this.parent.objType == ezOBJType.TXT)
                            {
                                Color c = new Color(p.R, p.G, p.B);
                                ezo = new ezTextBox(ezPage.this, x, y, width, height, c);
                            }
                            if(ezPage.this.activeOBJ != null)
                            {
                                activeOBJ.hideOutline();
                            }
                            ezPage.this.activeOBJ = ezo;
                            ezPage.this.add(ezo);
                        }
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        //JButton newBtn = new JButton();//try to new text box
                    }
                    status = ezPageStatus.ready2creatOBJ;
                }
            }
        });
        
    }
    public void paint(Graphics g)
    {
        
        //Color c = new Color(parent.R, parent.G, parent.B);
        for(ezDrawLine l:drawLines)
        {
            //int countcount=0;
            //countcount=l.Count;
            g.setColor(l.color);
            
            Graphics2D g2 = (Graphics2D) g;
            g2.drawLine(l.sp.x, l.sp.y, l.ep.x, l.ep.y);
            Stroke stroke=new BasicStroke(l.Count);
            g2.setStroke(stroke);
        }
    }
}
