package ezERD2016;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import javax.swing.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.imageio.ImageIO;


import javax.swing.JButton;
        
/**
 *
 * @author Thunder
 */
public class ezJToolBar extends JPanel implements ActionListener 
{
    ezERD parent;
    ezColor ezcolor;
    ezPage ezpage;
    ezOBJType objType;
    ezOBJ ezobj;
    
    Color linecolor;
    Color objcolor;
   
    String text;
    JFileChooser fc;
    
    public int R,G,B;
    public int input;
    
    static final private String NEWPAGE = "NewPage";
    static final private String PREVPAGE = "PrevPage";
    static final private String NEXTPAGE = "NextPage";
    static final private String DELPAGE = "DelPage";
    static final private String SAVE = "Save";
    static final private String DRAW = "Draw";
    static final private String ERASER = "Eraser";
    static final private String LINE = "Line";
    static final private String ENTITYSET = "EntitySet";
    static final private String RELATIONSHIP = "Relationship";
    static final private String ATTRIBUTE = "Attribute";
    static final private String TEXTBOX = "TextBox";
    static final private String DELOBJ = "DelOBJ";
    static final private String COLORCHOOSER = "ColorChooser";
    static final private String BRUSH_INPUT = "Brush_Input";
    static final private String EXIT = "Exit";
        
    public ezJToolBar(ezERD p) 
    {
        //super(new BorderLayout());
        super();
        parent = p;
        
        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);
        
        setPreferredSize(new Dimension(1024, 56));
        add(toolBar, BorderLayout.WEST);
        //add(scrollPane, BorderLayout.CENTER);
        toolBar.setFloatable(false);
    }
        
     public void addButtons(JToolBar toolBar)
     {
        JButton button = null;       //first button
        button = makeNavigationButton("newpage", NEWPAGE, "NewPage","NewPage");
        toolBar.add(button);        //second button
        button = makeNavigationButton("Entypo_e75d(11)_32", PREVPAGE, "PrevPage","PrevPage");
        toolBar.add(button);        //third button
        button = makeNavigationButton("Entypo_e75e(10)_32", NEXTPAGE, "NextPage","NextPage");
        toolBar.add(button);
        button = makeNavigationButton("Entypo_2796(8)_32", DELPAGE, "DelPage","DelPage");
        toolBar.add(button);
        button = makeNavigationButton("floppy-512", SAVE, "Save","Save");
        toolBar.add(button);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        button = makeNavigationButton("linea_240(4)_32", DRAW, "Draw","Draw");
        toolBar.add(button);
        button = makeNavigationButton("linea_275(2)_32", ERASER, "Eraser","Eraser");
        toolBar.add(button);
        button = makeNavigationButton("Editing-Line-icon", LINE, "Line","Line");
        toolBar.add(button);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        button = makeNavigationButton("linea_253(7)_32", ENTITYSET, "Entity Set","Entity Set");
        toolBar.add(button);
        button = makeNavigationButton("linea_283(5)_32", RELATIONSHIP, "Relationship","Relationship");
        toolBar.add(button);
        button = makeNavigationButton("linea_251(6)_32", ATTRIBUTE, "Attribute","Attribute");
        toolBar.add(button);
        button = makeNavigationButton("Text-Box-icon", TEXTBOX, "TextBox","TextBox");
        toolBar.add(button);
        button = makeNavigationButton("edit_delete", DELOBJ,"DELOBJ", "DELOBJ");
        toolBar.add(button);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        button = makeNavigationButton("linea_246(1)_32", COLORCHOOSER, "ColorChooser","ColorChooser");
        toolBar.add(button);
        /*button = makeNavigationButton("linea_245(3)_32", BRUSH, "Brush","Brush");
        toolBar.add(button);*/
        button = makeNavigationButton("FontAwesome_f045(0)_32", EXIT, "Exit","Exit");
        toolBar.add(button);
        
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        
        JTextField textField = new JTextField("Brush(1 to 8)");
        textField.addFocusListener(new FocusListener() {
            @Override public void focusLost(final FocusEvent pE) {}
            @Override public void focusGained(final FocusEvent pE) {
             textField.selectAll();
            }
        });
        
        textField.setColumns(10);
        textField.addActionListener(this);
        textField.setActionCommand(BRUSH_INPUT);
        toolBar.add(textField);
        
    }
     
    public JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText,String altText) 
    {
        //Look for the image.
        String imgLocation = "src/images/" + imageName + ".png";
        
        //URL imageURL = main.class.getResource(imgLocation);
        
       
        //Create and initialize the button.
        JButton button = new JButton(new ImageIcon("src/images/" + imageName + ".png"));
        
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imgLocation != null) {                      //image found
        button.setIcon(new ImageIcon(imgLocation, altText));
        }
        else {                                     //no image found
        button.setText(altText);
        System.err.println("Resource not found: " + imgLocation);
        }
        return button;
        
    }
     
    public void actionPerformed (ActionEvent e){
        String cmd = e.getActionCommand();
        //String description = null;

        // Handle each button.
        if (NEWPAGE.equals(cmd))  //first button clicked
        {            
            ezPage newPage = new ezPage(ezJToolBar.this);
            parent.mainWin.addPage(newPage);
            
            parent.totalPages++;
                            
            parent.messagebar.updateMessage();
        }
        else if (PREVPAGE.equals(cmd))  // second button clicked
        {
            if(parent.curPage !=1)
            {
                parent.mainWin.prevPage();
            
                parent.curPage--;
                parent.messagebar.updateMessage();
            }
        }
        else if (NEXTPAGE.equals(cmd))  // third button clicked
        {
            if(parent.totalPages != parent.curPage)
            {
                parent.mainWin.nextPage();
            
                parent.curPage++;
                parent.messagebar.updateMessage();
                //parent.activePage.status=ezPageStatus.inactivated;
            }
        }
        else if (DELPAGE.equals(cmd))  // second button clicked
        {
            parent.mainWin.delPage();
            //parent.curPage--;
           //parent.totalPages--;
            parent.messagebar.updateMessage();
        }
        else if(SAVE.equals(cmd))
        {
            if(parent.activePage.activeOBJ != null)
                   parent.activePage.activeOBJ.hideOutline();
            try {
        fc = new JFileChooser();
                Dimension size = parent.activePage.getSize();
                BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_BGR);
                parent.activePage.printAll(image.getGraphics());
                int returnVal = fc.showSaveDialog(parent.mainWin);
                
                if (returnVal == JFileChooser.APPROVE_OPTION) 
                {
                    File file = fc.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    if(!filePath.endsWith(".png")) 
                    {
                        file = new File(filePath + ".png");
                    }
                    //This is where a real application would save the file.
                    ImageIO.write(image, "png", file);
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        else if (DRAW.equals(cmd))  // third button clicked
        {
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status==ezPageStatus.activated)
                parent.activePage.status=ezPageStatus.ready2draw;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2draw;
            else if(parent.activePage.status == ezPageStatus.ready2creatOBJ)
                parent.activePage.status = ezPageStatus.ready2draw;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2draw;
        }
        else if (ERASER.equals(cmd))  // third button clicked
        {
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status==ezPageStatus.activated)
                parent.activePage.status=ezPageStatus.ready2erase;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2erase;
            else if(parent.activePage.status == ezPageStatus.ready2creatOBJ)
                parent.activePage.status = ezPageStatus.ready2erase;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2erase;
        }
        else if (LINE.equals(cmd))  // third button clicked
        {
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status==ezPageStatus.activated)
                parent.activePage.status=ezPageStatus.ready2line;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2line;
            else if(parent.activePage.status == ezPageStatus.ready2creatOBJ)
                parent.activePage.status = ezPageStatus.ready2line;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2line;
        }
        else if (ENTITYSET.equals(cmd))  // third button clicked
        {
            objType = ezOBJType.ES;
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status == ezPageStatus.activated)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
        }
        else if (RELATIONSHIP.equals(cmd))  // third button clicked
        {
            objType = ezOBJType.RS;
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status == ezPageStatus.activated)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
        }
        else if (ATTRIBUTE.equals(cmd))  // third button clicked
        {
            objType = ezOBJType.AT;
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status == ezPageStatus.activated)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
        }
        else if (TEXTBOX.equals(cmd))  // third button clicked
        {
            objType = ezOBJType.TXT;
            parent.activePage.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            if(parent.activePage.status == ezPageStatus.activated)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2draw)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2erase)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
            else if(parent.activePage.status == ezPageStatus.ready2line)
                parent.activePage.status = ezPageStatus.ready2creatOBJ;
        }
        else if (DELOBJ.equals(cmd))  // second button clicked
        {
            System.out.println("DEL");
            if(parent.activePage.activeOBJ != null && parent.activePage.activeOBJ.status == ezOBJStatus.selected)
            {
                parent.activePage.activeOBJ.hideOutline();
                parent.activePage.activeOBJ.status = ezOBJStatus.unselected;
                parent.activePage.remove(parent.activePage.activeOBJ);
                parent.activePage.activeOBJ = null;
            }
        }
        else if (COLORCHOOSER.equals(cmd))  // second button clicked
        {
            ezcolor = new ezColor(this);
            linecolor = ezcolor.color;
        }
        else if(BRUSH_INPUT.equals(cmd))
        {
            input = 1;
            JTextField tf =(JTextField)e.getSource();
            text = tf.getText();
            //System.out.println(text);
            try{
                input = Integer.parseInt(text);
                }
            catch(Exception ee){
                input = 1;
                }
            
        }
        else if(EXIT.equals(cmd))
        {
            System.exit(0);
        }
    }
    
}
