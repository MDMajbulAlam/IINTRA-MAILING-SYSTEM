package proj;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

public class MyDesktop_2 extends JDesktopPane
{
Image backimage;
public MyDesktop_2()
{
    try
    {

     backimage = new ImageIcon(getClass().getResource("f.jpg")).getImage();
         
    }
    catch(Exception ex)
    {
        
    }
}
  @Override
    protected void paintComponent(Graphics g)
    {
        if(backimage==null)
        {
            super.paintComponent(g);
        }
        else
        {
            g.drawImage(backimage,0,0,getWidth(),getHeight(),this);
        }
    }
}
