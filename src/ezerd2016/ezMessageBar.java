package ezERD2016;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Thunder
 */
public class ezMessageBar extends Panel 
{
    ezERD parent;
    String message;
    Label pageInfo;

    
    public void updatePageInfo()
    {
        pageInfo.setText(""+parent.curPage+"/"+parent.totalPages);
    }
    
    public void updateMessage()
    {
        updatePageInfo();
    }
    
    public void showMessage()
    {
        pageInfo = new Label(message);
        this.add(pageInfo);
    }
    
    ezMessageBar(ezERD p)
    {
        
        parent = p;
        this.setBackground(Color.LIGHT_GRAY);
        message = "1/1";
        this.setLayout(new FlowLayout());
        showMessage();
    }
}
